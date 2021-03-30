package com.app.auctionmanagement.payload;

import java.util.Date;

public class UpdateAuctionRequest {
    private String auctionName;
    private Integer minIncrement;
    private Date startDate;
    private Date endDate;

    public UpdateAuctionRequest(String auctionName, Integer minIncrement, Date startDate, Date endDate) {
        this.auctionName = auctionName;
        this.minIncrement = minIncrement;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
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
}
