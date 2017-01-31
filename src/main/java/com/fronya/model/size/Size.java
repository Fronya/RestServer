package com.fronya.model.size;


public class Size {
    private int id;
    private String value;

    public Size() {
    }

    public Size(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
