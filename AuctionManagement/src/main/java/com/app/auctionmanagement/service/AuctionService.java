package com.app.auctionmanagement.service;

import com.app.auctionmanagement.exception.ResourceNotFoundException;
import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.model.Product;
import com.app.auctionmanagement.model.User;
import com.app.auctionmanagement.payload.AuctionResult;
import com.app.auctionmanagement.payload.UpdateRequest;
import com.app.auctionmanagement.repository.AuctionRepository;
import com.app.auctionmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private UserService userService;

    public void saveAuction(Auction auction) {
        Product p = productRepository.findByProductName(auction.getProductName());
        auction.setProduct(p);
        auction.setStatus(AuctionStatus.OPEN);
        auctionRepository.save(auction);
    }

    public List<Auction> getAllAuctions() {
        List<Auction> auctions = new ArrayList<>();
        auctionRepository.findAll().forEach(auctions::add);
        return auctions;
    }

    public List<Auction> getAllOpenAuctions() {
        List<Auction> auctions = auctionRepository.findAll().stream()
                .filter(auction -> auction.getStatus().equals(AuctionStatus.OPEN))
                .collect(Collectors.toList());
        return auctions;
    }

    public User getResult(Long auctionId) {
        Optional<Auction> auction = auctionRepository.findById(auctionId);
        Long winnerUserId = auction.get().getWinnerUserId();
        User user = userService.getUserById(winnerUserId);
        return user;
    }

    public AuctionResult getResult(String auctionName) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));

        if (auction.getStatus().equals(AuctionStatus.OPEN)) {
            return new AuctionResult(AuctionStatus.OPEN, auctionName, "NA", 0, "Auction in Progress");
        } else {
            Long winnerUserId = auction.getWinnerUserId();
            if (winnerUserId == 0) {
                return new AuctionResult(AuctionStatus.CLOSED, auctionName, "NA", 0, "No Winner");
            } else {
                User user = userService.getUserById(winnerUserId);
                return new AuctionResult(AuctionStatus.CLOSED, auctionName, user.getName(),
                        auction.getWinningBidValue(), "Winner Awarded");
            }
        }
    }


    public void deleteAuction(String auctionName) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));
        auctionRepository.delete(auction);
    }

    public void updateAuction(String auctionName, UpdateRequest updateRequest) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));

        //TODO : Update other attributes
        if (Objects.nonNull(updateRequest.getEndDate())) {
            auction.setEndDate(updateRequest.getEndDate());
        }
        auctionRepository.save(auction);
    }

    public Map<String, AuctionStatus> auctionStatus() {
        Map<String, AuctionStatus> auctions = auctionRepository.findAll().stream()
                .collect(Collectors.toMap(Auction::getAuctionName, Auction::getStatus));
        return auctions;
    }
}
