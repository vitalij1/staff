package com.viro.staff.data;


import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.viro.staff.data.entity.Employee;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class EmployeeModel {

    private BriteDatabase db;

    @Inject
    public EmployeeModel(BriteDatabase db) {
        this.db = db;
    }

    public Observable<List<Employee>> getEmployees() {
        return db.createQuery(Employee.Table.TABLE_NAME, "SELECT * FROM " + Employee.Table.TABLE_NAME)
                .mapToList(this::toEmployee);
    }

    public Observable<Employee> getEmployee(int id) {
        return db.createQuery(Employee.Table.TABLE_NAME, "SELECT * FROM " + Employee.Table.TABLE_NAME
                + " WHERE "
                + Employee.Table.COLUMN_ID + "=" + id)
                .mapToOne(this::toEmployee);
    }

    public Long saveEmployee(Employee employee) {
        return db.insert(Employee.Table.TABLE_NAME, employee.toContentValues());
    }

    public boolean updateEmployee(Employee employee) {
        return db.update(Employee.Table.TABLE_NAME, employee.toContentValues(),
                Employee.Table.COLUMN_ID + "=" + employee.getId(), null) > 0;
    }

    private Employee toEmployee(Cursor cursor) {
        return new Employee.Builder()
                .id(cursor.getInt(cursor.getColumnIndex(Employee.Table.COLUMN_ID)))
                .firstName(cursor.getString(cursor.getColumnIndex(Employee.Table.COLUMN_FIRST_NAME)))
                .lastName(cursor.getString(cursor.getColumnIndex(Employee.Table.COLUMN_LAST_NAME)))
                .year(cursor.getInt(cursor.getColumnIndex(Employee.Table.COLUMN_BIRTHDAY_YEAR)))
                .city(cursor.getString(cursor.getColumnIndex(Employee.Table.COLUMN_CITY)))
                .position(cursor.getString(cursor.getColumnIndex(Employee.Table.COLUMN_POSITION)))
                .build();
    }
}
