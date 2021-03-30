package com.app.auctionmanagement.payload;

import java.util.Date;

public class BidRequest {

    private Integer bidValue;
    private String auctionName;

    public BidRequest(Integer bidValue, String auctionName, Date date) {
        this.bidValue = bidValue;
        this.auctionName = auctionName;
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }
}
