package com.app.auctionmanagement.payload;

import com.app.auctionmanagement.model.AuctionStatus;

public class AuctionResult {
    private AuctionStatus auctionStatus;
    private String auctionName;
    private String winnerName;
    private Integer bidValue;
    private String message;

    public AuctionResult(AuctionStatus auctionStatus, String auctionName, String winnerName, Integer bidValue, String message) {
        this.auctionStatus = auctionStatus;
        this.auctionName = auctionName;
        this.winnerName = winnerName;
        this.bidValue = bidValue;
        this.message = message;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}