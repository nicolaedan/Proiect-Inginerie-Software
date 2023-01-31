package com.parking.example.ejb;

import com.parking.example.entities.User;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Stateful
@SessionScoped
public class InvoiceBean implements Serializable {
    Set<Long> userIds = new HashSet<>();

    public Set<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(Set<Long> productsIds) {
        this.productsIds = productsIds;
    }

    Set<Long> productsIds = new HashSet<>();

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

    User user;

    public Long getId_user() {
        return user.getId();
    }
}
