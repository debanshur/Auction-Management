package com.app.auctionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_bid")
public class Bid implements Comparable<Bid> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bidId;

    private Integer bidValue;

    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus;

    private String auctionName;
    private Date date;

    @OneToOne()
    @JoinColumn(name = "auctionId")
    private Auction auction;

    @OneToOne()
    @JoinColumn(name = "id")
    private User user;

    public Bid(Long bidId, Integer bidValue, Date date, Auction auction, User user, BidStatus bidStatus) {
        this.bidId = bidId;
        this.bidValue = bidValue;
        this.date = date;
        this.auction = auction;
        this.user = user;
        this.bidStatus = bidStatus;
    }

    public Bid() {
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    @Override
    public int compareTo(Bid o) {
        int bidCompareValue = o.getBidValue().compareTo(this.getBidValue());
        if(bidCompareValue == 0) {
            return this.getDate().compareTo(o.getDate());
        }
        return bidCompareValue;
    }

    @Override
    public String toString() {
        return "Bid [bidId=" + bidId + ", bidValue=" + bidValue + ", bidStatus=" + bidStatus + ", date=" + date
                + ", auction=" + auction + ", user=" + user.getUsername() + "]";
    }


}
