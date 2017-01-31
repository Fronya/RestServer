package com.fronya.dao.size;


import com.fronya.model.size.Size;
import com.fronya.model.size.SizeProduct;

import java.util.List;

public interface SizeDao {
    boolean create(Size newSize);
    boolean update(Size newSize);
    boolean delete(int idSize);
    List<Size> getAll();
    Size get(int idSize);
    //SizeProduct get(int idProduct, int idSize);
}
