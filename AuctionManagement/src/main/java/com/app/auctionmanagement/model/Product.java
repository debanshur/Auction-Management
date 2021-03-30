package com.app.auctionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

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

    @NotBlank
    private String productName;
    private String description;
    private Boolean auctionStatus;


    public Product(String productName, String description, Boolean auctionStatus) {
        this.productName = productName;
        this.description = description;
        this.auctionStatus = auctionStatus;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
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

    public Boolean getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(Boolean auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
                + "]";
    }

}
