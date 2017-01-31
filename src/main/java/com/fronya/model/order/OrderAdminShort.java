package com.fronya.model.order;


public class OrderAdminShort {
    private int count;
    private double price;
    private String value;
    private String name;

    public OrderAdminShort() {
    }

    public OrderAdminShort(int count, double price, String value, String name) {
        this.count = count;
        this.price = price;
        this.value = value;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
