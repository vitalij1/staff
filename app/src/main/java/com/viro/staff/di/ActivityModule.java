package com.viro.staff.di;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.viro.staff.bus.MainFragmentBus;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    @PerActivity
    public SharedPreferences provideSharedPreferences(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
    }

    @PerActivity
    @Provides
    public FragmentManager provideFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

    @PerActivity
    @Provides
    public MainFragmentBus provideFragmentBus(FragmentManager manager, Bus bus, SharedPreferences preferences) {
        return new MainFragmentBus(manager, bus, preferences);
    }
}
