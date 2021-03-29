package com.app.auctionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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

    private String auctionName;
    private Integer minPrice;
    private Integer minIncrement;
    private Date startDate;
    private Date endDate;
    private AuctionStatus status;
    private Long winnerUserId;
    private Integer winningBidValue;
    private String productName;

    @OneToOne()
    @JoinColumn(name = "productId")
    private Product product;

    public Auction() {
    }

    public Auction(Long auctionId, String auctionName, Integer minPrice, Integer minIncrement, Date startDate, Date endDate,
                   AuctionStatus status, Long winnerUserId, Product product) {
        this.auctionId = auctionId;
        this.minPrice = minPrice;
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

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

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

    public void setWinnerUserId(Long l) {
        this.winnerUserId = l;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Integer getWinningBidValue() {
        return winningBidValue;
    }

    public void setWinningBidValue(Integer winningBidValue) {
        this.winningBidValue = winningBidValue;
    }


    @Override
    public String toString() {
        return "Auction [auctionId=" + auctionId + ", minPrice=" + minPrice + ", minIncrement=" + minIncrement
                + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", winnerUserId="
                + winnerUserId + ", product=" + product + ", user=" + "" + "]";
    }

}
