package com.app.auctionmanagement.service;

import com.app.auctionmanagement.exception.ResourceNotFoundException;
import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.model.Bid;
import com.app.auctionmanagement.model.BidStatus;
import com.app.auctionmanagement.model.User;
import com.app.auctionmanagement.payload.BidRequest;
import com.app.auctionmanagement.repository.AuctionRepository;
import com.app.auctionmanagement.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuctionRepository auctionRepository;

    public void createBid(BidRequest bidRequest, String bearerToken) {

        User user = tokenService.getUserDetails(bearerToken);

        Auction a = auctionRepository.findByAuctionName(bidRequest.getAuctionName())
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", bidRequest.getAuctionName()));


        if (a.getStatus().equals(AuctionStatus.CLOSED)) {
            throw new IllegalArgumentException("Auction is Closed");
        }

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        // First Bid for this auction
        if (a.getCurrentBidValue() == 0) {
            if (bidRequest.getBidValue() >= a.getStartPrice()) {
                a.setCurrentBidValue(bidRequest.getBidValue());
            } else {
                throw new IllegalArgumentException("Bid price lower than minimum price");
            }
        } else {
            if (bidRequest.getBidValue() >= a.getCurrentBidValue() + a.getMinIncrement()) {
                a.setCurrentBidValue(bidRequest.getBidValue());
            } else {
                throw new IllegalArgumentException("Bid price lower than minimum increment");
            }
        }

        Bid bid = new Bid(bidRequest.getBidValue(), date, a, user, BidStatus.CREATED);
        bidRepository.save(bid);
        auctionRepository.save(a);
    }

    public List<Bid> getBids(String productId) {
        return bidRepository.findByAuctionProduct(Long.valueOf(productId));
    }
}
