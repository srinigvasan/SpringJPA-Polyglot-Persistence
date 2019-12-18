package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "productId")
    private int productId;

    @Column(name="productName")
    private String productName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public Product() {
    }
}
