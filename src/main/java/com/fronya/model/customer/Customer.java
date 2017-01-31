package com.fronya.model.customer;


import com.fronya.model.role.Role;

public class Customer {
    private int id;
    private String name;
    private String lastName;
    private String city;
    private String address;
    private String phone;
    private String email;
    private Role role;


    public Customer(int id, String name, String lastName, String city, String address,
                    String phone, String email, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
