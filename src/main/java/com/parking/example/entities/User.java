package com.parking.example.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {

    private Long money_deposited;

    public Long getMoney_deposited() {
        return money_deposited;
    }

    public void setMoney_deposited(Long money_deposited) {
        this.money_deposited = money_deposited;
    }

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private String username;

    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
