package com.viro.staff.di;


import com.viro.staff.ui.MainActivity;
import com.viro.staff.ui.employee.create.EmployeeEditFragment;
import com.viro.staff.ui.employee.create.EmployeeEditPresenterImpl;
import com.viro.staff.ui.employee.list.EmployeeListPresenterImpl;

import dagger.Component;

@PerActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(EmployeeEditFragment fragment);
    void inject(EmployeeEditPresenterImpl presenter);
    void inject(EmployeeListPresenterImpl presenter);
}
