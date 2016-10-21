package com.viro.staff.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.viro.staff.R;
import com.viro.staff.StaffApplication;
import com.viro.staff.bus.event.EmployeeEvent;
import com.viro.staff.bus.event.GoBackEvent;
import com.viro.staff.ui.employee.create.EmployeeEditFragment;
import com.viro.staff.ui.employee.create.EmployeeEditFragmentDialog;
import com.viro.staff.ui.employee.list.EmployeeListFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_DEFAULT_VIEW = "defaultView";

    @Inject
    Bus bus;

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StaffApplication.getComponent(this).inject(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_layout, EmployeeListFragment.newInstance())
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Subscribe
    public void onGoBackEvent(GoBackEvent goBackEvent) {
        getFragmentManager().popBackStack();
    }

    @Subscribe
    public void goOnEditScreen(EmployeeEvent employeeEvent) {
        if (preferences.getBoolean(TAG_DEFAULT_VIEW, false)) {
            EmployeeEditFragmentDialog.newInstance(employeeEvent.getId())
                    .show(getFragmentManager(), "NewDialog");
        } else {
            getFragmentManager().beginTransaction()
                    .addToBackStack("EditFragment")
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.main_layout, EmployeeEditFragment.newInstance(employeeEvent.getId()), "EditFragment")
                    .commit();
        }
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
