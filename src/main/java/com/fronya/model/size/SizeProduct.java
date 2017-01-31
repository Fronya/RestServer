package com.fronya.model.size;


public class SizeProduct extends Size {
    private double price;
    private boolean isExist;

    public SizeProduct(int id, String value, double price, boolean isExist) {
        super(id, value);
        this.price = price;
        this.isExist = isExist;
    }

    public SizeProduct(int id, String value, double price) {
        super(id, value);
        this.price = price;
        this.isExist = true;
    }

    public double getPrice() {
        return price;
    }

    public boolean isExist() {
        return isExist;
    }
}
