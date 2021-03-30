package com.app.auctionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "auction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "auctionName"
        })
})
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auctionId;

    @NotBlank
    private String auctionName;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date endDate;

    private Integer startPrice;
    private Integer minIncrement;
    private AuctionStatus status;
    private Long winnerUserId;
    private Integer currentBidValue;

    @OneToOne()
    @JoinColumn(name = "productId")
    private Product product;

    public Auction() {
    }

    public Auction(String auctionName, Integer startPrice, Integer minIncrement, Date startDate, Date endDate,
                   AuctionStatus status, Product product) {
        this.auctionName = auctionName;
        this.startPrice = startPrice;
        this.currentBidValue = 0;
        this.minIncrement = minIncrement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.product = product;
    }

    public Auction(String auctionName, Integer startPrice, Integer minIncrement, Date startDate, Date endDate,
                   AuctionStatus status, Long winnerUserId, Product product) {
        this.auctionName = auctionName;
        this.startPrice = startPrice;
        this.currentBidValue = 0;
        this.minIncrement = minIncrement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.winnerUserId = winnerUserId;
        this.product = product;
    }


    public Long getAuctionId() {
        return auctionId;
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

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public Long getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(Long l) {
        this.winnerUserId = l;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Integer getCurrentBidValue() {
        return currentBidValue;
    }

    public void setCurrentBidValue(Integer currentBidValue) {
        this.currentBidValue = currentBidValue;
    }


    @Override
    public String toString() {
        return "Auction [auctionId=" + auctionId + ", startPrice=" + startPrice + ", minIncrement=" + minIncrement
                + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", winnerUserId="
                + winnerUserId + ", product=" + product + ", user=" + "" + "]";
    }

}
