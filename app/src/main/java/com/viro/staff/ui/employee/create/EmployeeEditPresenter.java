package com.viro.staff.ui.employee.create;


import com.viro.staff.data.entity.Employee;
import com.viro.staff.ui.common.BaseView;
import com.viro.staff.ui.common.Presenter;

public interface EmployeeEditPresenter extends Presenter<EmployeeEditPresenter.EmployeeEditView> {

    void loadEmployee(int id);
    void saveEmployee(Employee employee);

    public interface EmployeeEditView extends BaseView {
        void onEmployeeLoaded(Employee employee);
        void onSaved();
        void onError(String error);
    }
}
