package com.app.auctionmanagement.payload;

import com.app.auctionmanagement.model.AuctionStatus;

import java.util.Date;

public class UpdateRequest {
    private Integer minPrice;
    private Integer minIncrement;
    private Date startDate;
    private Date endDate;
    private AuctionStatus status;
    private Long winnerUserId;


    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
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

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public Long getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(Long winnerUserId) {
        this.winnerUserId = winnerUserId;
    }
}
