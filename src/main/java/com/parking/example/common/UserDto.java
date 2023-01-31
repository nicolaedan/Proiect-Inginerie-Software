package com.parking.example.common;

public class UserDto {
    String email;
    String password;
    String username;
    Long id;
    Long money_deposited;

    public UserDto(String email, String password, String username, Long id,Long money_deposited) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.id = id;
        this.money_deposited=money_deposited;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public Long getMoney_deposited() {
        return money_deposited;
    }
}