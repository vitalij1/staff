package com.viro.staff.bus.event;


public class EmployeeEvent {

    private int id;

    public EmployeeEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
