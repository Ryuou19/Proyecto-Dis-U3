package com.icc.identity_svc.domain;

public class AccountProfileDTO {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String street;
    private String city;

    public AccountProfileDTO() {}

    public String getEmail() {
        return email;
    }

    public AccountProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountProfileDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountProfileDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountProfileDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AccountProfileDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AccountProfileDTO setCity(String city) {
        this.city = city;
        return this;
    }
}
