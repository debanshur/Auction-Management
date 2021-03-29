package com.app.auctionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "productName"
        })
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;
    private String description;


    public Product(Long productId, String productName, String description) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.description = description;
    }


    public Product() {
        super();
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
                + "]";
    }

}
