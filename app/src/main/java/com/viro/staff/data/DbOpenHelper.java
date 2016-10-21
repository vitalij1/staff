package com.viro.staff.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.viro.staff.data.entity.Employee;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 7;
    private static final String DB_NAME = "Staff";

    private static final String CREATE_EMPLOYEE_TABLE_QUERY = ""
            + "CREATE TABLE " + Employee.Table.TABLE_NAME + "("
            + Employee.Table.COLUMN_ID + " INTEGER PRIMARY KEY, "
            + Employee.Table.COLUMN_FIRST_NAME + " TEXT, "
            + Employee.Table.COLUMN_LAST_NAME + " TEXT, "
            + Employee.Table.COLUMN_CITY + " TEXT, "
            + Employee.Table.COLUMN_BIRTHDAY_YEAR + " INTEGER, "
            + Employee.Table.COLUMN_POSITION + " TEXT)";


    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Employee.Table.TABLE_NAME);
        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE_QUERY);
    }
}
