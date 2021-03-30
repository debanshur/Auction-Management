package com.app.auctionmanagement.controller;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.payload.AuctionRequest;
import com.app.auctionmanagement.payload.AuctionResult;
import com.app.auctionmanagement.payload.UpdateAuctionRequest;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PreAuthorize("@securityService.validateKey(#secret)")
    @PostMapping("/create")
    public void createAuction(@RequestHeader("secret") String secret,
                              @Valid @RequestBody AuctionRequest auctionRequest) {
        auctionService.createAuction(auctionRequest);
    }

    @GetMapping("/open")
    public List<Auction> getAllOpenAuctions() {
        return auctionService.getAllOpenAuctions();
    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @GetMapping("/all")
    public List<Auction> getAuctions(@RequestHeader("secret") String secret) {
        return auctionService.getAllAuctions();
    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @GetMapping("/result/{auctionName}")
    public AuctionResult getResult(@PathVariable String auctionName,
                                   @RequestHeader("secret") String secret) {
        return auctionService.getResult(auctionName);
    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @PostMapping("/delete/{auctionName}")
    public void deleteAuction(@PathVariable(value = "auctionName") String auctionName,
                              @RequestHeader("secret") String secret) {
        auctionService.deleteAuction(auctionName);

    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @PutMapping("/update/{auctionName}")
    public void updateAuction(@PathVariable(value = "auctionName") String auctionName,
                              @Valid @RequestBody UpdateAuctionRequest updateAuctionRequest,
                              @RequestHeader("secret") String secret) {
        auctionService.updateAuction(auctionName, updateAuctionRequest);
    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @GetMapping("/status")
    public Map<String, AuctionStatus> auctionStatus(@RequestHeader("secret") String secret) {
        return auctionService.auctionStatus();
    }

    @PreAuthorize("@securityService.validateKey(#secret)")
    @PostMapping("/close/{auctionName}")
    public void closeAuction(@PathVariable(value = "auctionName") String auctionName,
                             @RequestHeader("secret") String secret) {
        auctionService.closeAuction(auctionName);

    }

}
