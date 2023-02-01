package com.parking.example.common;

public class ProductCartDto {
    private Long id_product;
    private Long id_user;
    private String state;
    private Long id;
    private String quantity;


    public ProductCartDto(Long id_product, Long id_user, String state, Long id, String quantity) {
        this.id_product = id_product;
        this.id_user = id_user;
        this.state = state;
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
