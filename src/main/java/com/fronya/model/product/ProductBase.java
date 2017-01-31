package com.fronya.model.product;


public class ProductBase {
    private int id;
    private String name;
    private String pathImage;

    public ProductBase(){}

    public ProductBase(int id, String name, String pathImage) {
        this.id = id;
        this.name = name;
        this.pathImage = pathImage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPathImage() {
        return pathImage;
    }
}
