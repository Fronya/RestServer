package com.fronya.dao.mysql.factory;


import com.fronya.dao.customer.CustomerDao;
import com.fronya.dao.factory.FactoryDao;
import com.fronya.dao.mysql.customer.MysqlCustomerDao;
import com.fronya.dao.mysql.order.MysqlOrderDao;
import com.fronya.dao.mysql.product.MysqlProductDao;
import com.fronya.dao.mysql.size.MysqlSizeDao;
import com.fronya.dao.order.OrderDao;
import com.fronya.dao.product.ProductDao;
import com.fronya.dao.size.SizeDao;

public class MysqlFactoryDao extends FactoryDao {
    @Override
    public CustomerDao getCustomerDao() {
        return new MysqlCustomerDao();
    }

    @Override
    public ProductDao getProductDao() {
        return new MysqlProductDao();
    }

    @Override
    public SizeDao getSizeDao() {return new MysqlSizeDao();}

    @Override
    public OrderDao getOrderDao() {
        return new MysqlOrderDao();
    }
}
