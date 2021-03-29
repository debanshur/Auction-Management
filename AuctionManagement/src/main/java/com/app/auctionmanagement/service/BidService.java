package com.app.auctionmanagement.service;

import com.app.auctionmanagement.exception.ResourceNotFoundException;
import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.Bid;
import com.app.auctionmanagement.model.BidStatus;
import com.app.auctionmanagement.model.User;
import com.app.auctionmanagement.repository.AuctionRepository;
import com.app.auctionmanagement.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    public void saveBid(Bid bid, String bearerToken) {

        User user = tokenService.getUserDetails(bearerToken);
        bid.setUser(user);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        Date date = new Date();

        bid.setBidStatus(BidStatus.CREATED);
        bid.setDate(date);
        Auction a = auctionRepository.findByAuctionName(bid.getAuctionName())
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", bid.getAuctionName()));
        bid.setAuction(a);
        bidRepository.save(bid);
    }

    public List<Bid> getBids(String productId) {
        return bidRepository.findByAuctionProduct(Long.valueOf(productId));
    }
}
