package com.fronya.model.order;


import java.util.List;

public class OrderList {
    private int id;
    private List<Order> orders;

    public OrderList() {
    }

    public OrderList(int id, List<Order> orders) {
        this.id = id;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
