package com.fronya.model.product;


public class ProductShort extends ProductBase {
    private double minPrice;

    public ProductShort(int id, String name, String pathImage, double minPrice) {
        super(id, name, pathImage);
        this.minPrice = minPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }
}
