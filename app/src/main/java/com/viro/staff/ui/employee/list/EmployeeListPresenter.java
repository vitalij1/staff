package com.viro.staff.ui.employee.list;


import android.app.FragmentManager;

import com.viro.staff.data.entity.Employee;
import com.viro.staff.ui.common.BaseView;
import com.viro.staff.ui.common.Presenter;

import java.util.List;

public interface EmployeeListPresenter extends Presenter<EmployeeListPresenter.EmployeeListView> {

    void onEmployeeClick(FragmentManager manager, Employee employee);
    void onAddClick(FragmentManager manager);
    void loadItems();

    interface EmployeeListView extends BaseView {
        void onEmployeeLoaded(List<Employee> items);
    }
}
