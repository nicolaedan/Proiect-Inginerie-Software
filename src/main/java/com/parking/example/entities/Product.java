package com.parking.example.entities;

import jakarta.persistence.*;

@Entity
public class Product {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String quantity;

    @Basic
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    private String category;
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Basic
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;

    }
    private ProductPhoto photo;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public ProductPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ProductPhoto photo) {
        this.photo = photo;
    }
}
