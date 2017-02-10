package com.fronya.dao.factory;

import com.fronya.dao.customer.CustomerDao;
import com.fronya.dao.mysql.factory.MysqlFactoryDao;
import com.fronya.dao.order.OrderDao;
import com.fronya.dao.product.ProductDao;
import com.fronya.dao.size.SizeDao;
import com.fronya.dao.size.SizeProductDao;

/**
 * Abstract factory
 */
public abstract class FactoryDao {
    //List of DAO types supported by the factory
    public static final int MYSQL = 1;

    public abstract CustomerDao getCustomerDao();
    public abstract ProductDao getProductDao();
    public abstract SizeDao getSizeDao();
    public abstract OrderDao getOrderDao();
    public abstract SizeProductDao getSizeProductDao();

    public static FactoryDao getFactoryDao(int whichFactory){
        switch (whichFactory){
            case MYSQL:
                return new MysqlFactoryDao();
            default:
                return null;
        }
    }
}
