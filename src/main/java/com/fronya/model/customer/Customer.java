package com.fronya.model.customer;


import com.fronya.model.role.Role;

public class Customer extends RegisterCustomer {
    private int id;
    private Role role;

    public Customer() {
    }

    public Customer(String name, String lastName, String city, String address, String phone, String email, String password, int id, Role role) {
        super(name, lastName, city, address, phone, email, password);
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }
}
