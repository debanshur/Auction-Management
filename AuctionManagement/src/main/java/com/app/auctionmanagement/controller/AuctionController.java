package com.app.auctionmanagement.controller;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.payload.AuctionResult;
import com.app.auctionmanagement.payload.UpdateRequest;
import com.app.auctionmanagement.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("")
    public void saveAuction(@RequestBody Auction auction) {
        auctionService.saveAuction(auction);
    }

    @GetMapping("/open")
    public List<Auction> getAllOpenAuctions(@RequestHeader("secret") String secret) {
        return auctionService.getAllOpenAuctions();
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @GetMapping("/all")
    public List<Auction> getAuctions(@RequestHeader("secret") String secret) {
        return auctionService.getAllAuctions();
    }

    @GetMapping("/result/{auctionName}")
    public AuctionResult getResult(@PathVariable String auctionName) {
        return auctionService.getResult(auctionName);
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @PostMapping("/delete/{auctionName}")
    public void deleteAuction(@PathVariable(value = "auctionName") String auctionName,
                              @RequestHeader("secret") String secret) {
        auctionService.deleteAuction(auctionName);

    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @PutMapping("/update/{auctionName}")
    public void updateAuction(@PathVariable(value = "auctionName") String auctionName,
                              @RequestBody UpdateRequest update,
                              @RequestHeader("secret") String secret) {
        auctionService.updateAuction(auctionName, update);
    }

    @PreAuthorize("@securityService.hasKey(#secret)")
    @GetMapping("/status")
    public Map<String, AuctionStatus> auctionStatus(@RequestHeader("secret") String secret) {
        return auctionService.auctionStatus();
    }

}
