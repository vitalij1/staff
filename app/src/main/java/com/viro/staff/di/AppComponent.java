package com.viro.staff.di;


import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    SqlBrite getSqlBrite();

    BriteDatabase getBriteDatabase();

}
