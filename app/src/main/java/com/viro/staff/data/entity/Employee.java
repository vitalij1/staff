package com.viro.staff.data.entity;


import android.content.ContentValues;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private int year;
    private String position;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public String getPosition() {
        return position;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Table.COLUMN_FIRST_NAME, firstName);
        contentValues.put(Table.COLUMN_LAST_NAME, lastName);
        contentValues.put(Table.COLUMN_CITY, city);
        contentValues.put(Table.COLUMN_POSITION, position);
        contentValues.put(Table.COLUMN_BIRTHDAY_YEAR, year);
        return contentValues;
    }

    public interface Table {
        String TABLE_NAME = "Employee";
        String COLUMN_ID = "_id";
        String COLUMN_FIRST_NAME = "first_name";
        String COLUMN_LAST_NAME = "last_name";
        String COLUMN_BIRTHDAY_YEAR = "year";
        String COLUMN_CITY = "city";
        String COLUMN_POSITION = "position";
    }

    public static final class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String city;
        private int year;
        private String position;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.id = this.id;
            employee.lastName = this.lastName;
            employee.firstName = this.firstName;
            employee.position = this.position;
            employee.year = this.year;
            employee.city = this.city;
            return employee;
        }
    }
}
