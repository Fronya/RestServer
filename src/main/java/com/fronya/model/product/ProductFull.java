package com.fronya.model.product;

import com.fronya.model.size.SizeProduct;

import java.util.List;

public class ProductFull extends ProductBase{
    private String description;
    private List<SizeProduct> sizes;
    private int idType;

    public ProductFull(){}

    public ProductFull(int id, String name, String pathImage, String description, int idType) {
        super(id, name, pathImage);
        this.description = description;
        this.idType = idType;
    }

    public String getDescription() {
        return description;
    }

    public int getIdType() {
        return idType;
    }

    public List<SizeProduct> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeProduct> sizes) {
        this.sizes = sizes;
    }
}
