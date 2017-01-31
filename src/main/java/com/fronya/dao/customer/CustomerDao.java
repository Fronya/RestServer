package com.fronya.dao.customer;


import com.fronya.model.customer.Customer;
import com.fronya.model.customer.RegisterCustomer;

public interface CustomerDao {
    int create(RegisterCustomer newCustomer);
    //boolean update(Customer updateCustomer);
   // boolean delete(int idCustomer);
    Customer get(String email, String password);
    int getId(String email, String password);
}
