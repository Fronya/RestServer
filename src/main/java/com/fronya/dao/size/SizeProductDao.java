package com.fronya.dao.size;


import com.fronya.model.size.SizeProduct;

public interface SizeProductDao {
    boolean create(SizeProduct[] sizes, int idProduct);
    boolean update(SizeProduct[] sizes, int idProduct);
}
