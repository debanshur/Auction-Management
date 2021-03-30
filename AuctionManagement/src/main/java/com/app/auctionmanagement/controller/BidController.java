package com.app.auctionmanagement.controller;

import com.app.auctionmanagement.model.Bid;
import com.app.auctionmanagement.payload.BidRequest;
import com.app.auctionmanagement.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    private BidService bidService;


    @PostMapping("/create")
    public void createBid(@Valid @RequestBody BidRequest bid, @RequestHeader("Authorization") String bearerToken) {
        bidService.createBid(bid, bearerToken);
    }

    @GetMapping("/{productId}")
    public List<Bid> getBids(@PathVariable("productId") String productId) {
        return bidService.getBids(productId);
    }
}
