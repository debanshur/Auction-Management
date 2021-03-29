package com.app.auctionmanagement.controller;

import com.app.auctionmanagement.model.Bid;
import com.app.auctionmanagement.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BidController {

    @Autowired
    private BidService bidService;


    @PostMapping("/bid")
    public void saveBid(@RequestBody Bid bid, @RequestHeader("Authorization") String bearerToken) {
        bidService.saveBid(bid, bearerToken);
    }

    @GetMapping("/bid/{productId}")
    public List<Bid> getBids(@PathVariable("productId") String productId) {
        return bidService.getBids(productId);
    }
}
