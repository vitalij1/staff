package com.viro.staff.di;


import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.viro.staff.data.DbOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;

@Module
public class DbModule {

    @Provides
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(Application application) {
        return new DbOpenHelper(application);
    }

    @Provides
    @Singleton
    public SqlBrite provideSqlBrite() {
        return SqlBrite.create();
    }

    @Provides
    @Singleton
    public BriteDatabase provideBriteDatabase(SqlBrite sqlBrite, SQLiteOpenHelper helper) {
        return sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    }

}
