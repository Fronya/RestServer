package com.fronya.dao.order;


import com.fronya.model.order.OrderAdmin;
import com.fronya.model.order.OrderList;

import java.util.List;

public interface OrderDao {
    boolean createOrder(OrderList list, int idUser);
    List<OrderAdmin> getAllOrders();
    boolean deleteOrder(int idOrder);
}
