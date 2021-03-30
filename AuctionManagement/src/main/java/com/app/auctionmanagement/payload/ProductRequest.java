package com.app.auctionmanagement.payload;

public class ProductRequest {
    private String productName;
    private String description;

    public ProductRequest(String productName, String description) {
        this.productName = productName;
        this.description = description;
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
}
