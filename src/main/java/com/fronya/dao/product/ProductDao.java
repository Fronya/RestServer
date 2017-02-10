package com.fronya.dao.product;


import com.fronya.model.product.ProductFull;
import com.fronya.model.product.ProductShort;

import java.util.List;

public interface ProductDao {
    int create(ProductFull newProduct);
    boolean update(ProductFull updateProduct);
    boolean delete(int idProduct);
    ProductFull get(int id);
    List<ProductShort> getAll(int pageNum, int typePerfume);
}
