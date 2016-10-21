package com.viro.staff.ui.employee.list;

import android.app.FragmentManager;
import android.content.Context;

import com.squareup.otto.Bus;
import com.viro.staff.StaffApplication;
import com.viro.staff.data.EmployeeModel;
import com.viro.staff.data.entity.Employee;
import com.viro.staff.bus.event.EmployeeEvent;
import com.viro.staff.ui.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class EmployeeListPresenterImpl extends BasePresenter<EmployeeListPresenter.EmployeeListView>
        implements EmployeeListPresenter {

    @Inject
    EmployeeModel model;

    @Inject
    Bus bus;

    private Subscription subscription;

    public EmployeeListPresenterImpl(EmployeeListView view) {
        super(view);
    }

    @Override
    public void onEmployeeClick(FragmentManager manager, Employee employee) {
        bus.post(new EmployeeEvent(employee.getId()));
    }

    @Override
    public void onAddClick(FragmentManager context) {
        bus.post(new EmployeeEvent(0));
    }

    @Override
    public void loadItems() {
        subscription = model.getEmployees()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> {
                    getView().onEmployeeLoaded(employees);
                });
    }

    @Override
    public void onAttach(Context context) {
        StaffApplication.getComponent(context).inject(this);
    }

    @Override
    public void onDetach() {
        subscription.unsubscribe();
    }
}
