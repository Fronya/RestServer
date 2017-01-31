package com.fronya.model.order;


public class Order {
    private int idSizeProduct;
    private String nameProduct;
    private String sizeValue;
    private int count;

    public Order() {
    }

    public Order(int idSizeProduct, String nameProduct, String sizeValue, int count) {
        this.idSizeProduct = idSizeProduct;
        this.nameProduct = nameProduct;
        this.sizeValue = sizeValue;
        this.count = count;
    }

    public int getIdSizeProduct() {
        return idSizeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public int getCount() {
        return count;
    }
}
