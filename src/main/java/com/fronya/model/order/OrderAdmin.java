package com.fronya.model.order;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderAdmin {
    private int idCustomer;
    private int idOrder;
    private Date date;
    private Time time;
    private List<OrderAdminShort> orders;

    public OrderAdmin(int idCustomer, int idOrder, Date date, Time time) {
        this.idCustomer = idCustomer;
        this.idOrder = idOrder;
        this.date = date;
        this.time = time;
        orders = new ArrayList<>();
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public List<OrderAdminShort> getOrders() {
        return orders;
    }

    public void addOrder(OrderAdminShort  newOrder){
        orders.add(newOrder);
    }
}
