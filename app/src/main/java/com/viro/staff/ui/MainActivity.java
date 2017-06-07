package com.viro.staff.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.viro.staff.R;
import com.viro.staff.StaffApplication;
import com.viro.staff.bus.MainFragmentBus;
import com.viro.staff.di.ActivityComponent;
import com.viro.staff.di.ActivityModule;
import com.viro.staff.di.DaggerActivityComponent;
import com.viro.staff.ui.employee.list.EmployeeListFragment;

import javax.inject.Inject;

import static com.viro.staff.bus.MainFragmentBus.TAG_DEFAULT_VIEW;

public class MainActivity extends AppCompatActivity {

    private ActivityComponent component;

    @Inject
    MainFragmentBus bus;

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = DaggerActivityComponent.builder()
                .appComponent(StaffApplication.getComponent(this))
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_layout, EmployeeListFragment.newInstance())
                .commit();
    }

    public ActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.asDialog).setChecked(preferences.getBoolean(TAG_DEFAULT_VIEW, false));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.asDialog) {
            item.setChecked(!item.isChecked());
            preferences.edit()
                    .putBoolean(TAG_DEFAULT_VIEW, item.isChecked())
                    .apply();
        }
        return super.onOptionsItemSelected(item);
    }
}
