package com.viro.staff.bus;


import android.app.FragmentManager;
import android.content.SharedPreferences;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.viro.staff.R;
import com.viro.staff.bus.event.EmployeeEvent;
import com.viro.staff.bus.event.GoBackEvent;
import com.viro.staff.ui.employee.create.EmployeeEditFragment;
import com.viro.staff.ui.employee.create.EmployeeEditFragmentDialog;

import javax.inject.Inject;

public class MainFragmentBus {

    public static final String TAG_DEFAULT_VIEW = "defaultView";

    private FragmentManager manager;
    private Bus bus;
    private SharedPreferences preferences;

    @Inject
    public MainFragmentBus(FragmentManager manager, Bus bus, SharedPreferences preferences) {
        this.manager = manager;
        this.bus = bus;
        this.preferences = preferences;
    }

    public void register() {
        bus.register(this);
    }

    public void unregister() {
        bus.unregister(this);
    }

    @Subscribe
    public void onGoBackEvent(GoBackEvent goBackEvent) {
        manager.popBackStack();
    }

    @Subscribe
    public void goOnEditScreen(EmployeeEvent employeeEvent) {
        if (preferences.getBoolean(TAG_DEFAULT_VIEW, false)) {
            EmployeeEditFragmentDialog.newInstance(employeeEvent.getId())
                    .show(manager, "NewDialog");
        } else {
            manager.beginTransaction()
                    .addToBackStack("EditFragment")
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.main_layout, EmployeeEditFragment.newInstance(employeeEvent.getId()), "EditFragment")
                    .commit();
        }
    }

    public void post(Object object) {
        bus.post(object);
    }

}
