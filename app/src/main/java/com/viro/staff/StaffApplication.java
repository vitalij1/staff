package com.viro.staff;

import android.app.Application;
import android.content.Context;

import com.viro.staff.di.AppComponent;
import com.viro.staff.di.AppModule;
import com.viro.staff.di.DaggerAppComponent;

public class StaffApplication extends Application {

    private AppComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((StaffApplication) context.getApplicationContext()).applicationComponent;
    }

}
