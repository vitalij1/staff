package com.viro.staff.di;


import com.viro.staff.ui.MainActivity;
import com.viro.staff.ui.employee.create.EmployeeEditFragment;
import com.viro.staff.ui.employee.create.EmployeeEditPresenterImpl;
import com.viro.staff.ui.employee.list.EmployeeListPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(EmployeeListPresenterImpl employeeListPresenter);

    void inject(EmployeeEditPresenterImpl employeeEditPresenter);

    void inject(MainActivity mainActivity);

    void inject(EmployeeEditFragment employeeEditFragment);
}
