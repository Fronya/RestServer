package com.fronya.model.customer;


public class LoginCustomer {
    private String email;
    private String password;

    public LoginCustomer() {
    }

    public LoginCustomer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
