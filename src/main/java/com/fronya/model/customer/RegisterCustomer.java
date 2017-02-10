package com.fronya.model.customer;


public class RegisterCustomer  {
    private String name;
    private String lastName;
    private String city;
    private String address;
    private String phone;
    private String email;
    private String password;

    public RegisterCustomer() {
    }

    public RegisterCustomer(String name, String lastName, String city, String address, String phone, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
