package com.example.prm_bookingfield.dtos;

import java.util.List;

public class FieldAndSchedule {
    private Field field;
    private List<String> listFieldSchedule;

    public FieldAndSchedule() {
    }

    public FieldAndSchedule(Field field, List<String> listFieldSchedule) {
        this.field = field;
        this.listFieldSchedule = listFieldSchedule;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<String> getListFieldSchedule() {
        return listFieldSchedule;
    }

    public void setListFieldSchedule(List<String> listFieldSchedule) {
        this.listFieldSchedule = listFieldSchedule;
    }
}
