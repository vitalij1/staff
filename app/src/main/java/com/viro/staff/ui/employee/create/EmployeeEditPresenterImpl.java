package com.viro.staff.ui.employee.create;


import android.content.Context;

import com.viro.staff.StaffApplication;
import com.viro.staff.data.EmployeeModel;
import com.viro.staff.data.entity.Employee;
import com.viro.staff.ui.common.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class EmployeeEditPresenterImpl extends BasePresenter<EmployeeEditPresenter.EmployeeEditView>
        implements EmployeeEditPresenter {

    @Inject
    EmployeeModel model;

    private Subscription subscription;

    public EmployeeEditPresenterImpl(EmployeeEditView view) {
        super(view);
    }

    @Override
    public void onAttach(Context context) {
        StaffApplication.getComponent(context).inject(this);
    }

    @Override
    public void onDetach() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadEmployee(int id) {
        subscription = model.getEmployee(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(employee -> {
                    getView().onEmployeeLoaded(employee);
                });
    }

    @Override
    public void saveEmployee(Employee employee) {
        if (validate(employee)) {
            if (employee.getId() == 0) {
                if (model.saveEmployee(employee) > 0) {
                    getView().onSaved();
                }
            } else {
                if (model.updateEmployee(employee)) {
                    getView().onSaved();
                }
            }
        }

    }

    private boolean validate(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            getView().onError("Имя обязательное поле");
            return false;
        } else if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
            getView().onError("Фамилия обязательное поле");
            return false;
        } else if (employee.getYear() == 0) {
            getView().onError("Год рождения обязательное поле");
            return false;
        } else if (employee.getPosition() == null || employee.getPosition().isEmpty()) {
            getView().onError("Выберите должность");
            return false;
        }else if (employee.getCity() == null || employee.getCity().isEmpty()) {
            getView().onError("Выберите город");
            return false;
        }
        return true;
    }
}
