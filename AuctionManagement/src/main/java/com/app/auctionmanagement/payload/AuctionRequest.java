package com.app.auctionmanagement.payload;

import java.util.Date;

public class AuctionRequest {
    private String auctionName;
    private Integer startPrice;
    private Integer minIncrement;
    private Date startDate;
    private Date endDate;
    private String productName;

    public AuctionRequest(String auctionName, Integer startPrice, Integer minIncrement, Date startDate, Date endDate, String productName) {
        this.auctionName = auctionName;
        this.startPrice = startPrice;
        this.minIncrement = minIncrement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productName = productName;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getMinIncrement() {
        return minIncrement;
    }

    public void setMinIncrement(Integer minIncrement) {
        this.minIncrement = minIncrement;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
