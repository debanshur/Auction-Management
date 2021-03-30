package com.app.auctionmanagement.service;

import com.app.auctionmanagement.exception.ResourceNotFoundException;
import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.model.Product;
import com.app.auctionmanagement.model.User;
import com.app.auctionmanagement.payload.AuctionRequest;
import com.app.auctionmanagement.payload.AuctionResult;
import com.app.auctionmanagement.payload.UpdateAuctionRequest;
import com.app.auctionmanagement.repository.AuctionRepository;
import com.app.auctionmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public void createAuction(AuctionRequest auctionRequest) {

        Optional<Product> p = productRepository.findByProductName(auctionRequest.getProductName());
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Product", "productName", auctionRequest.getProductName());
        }

        if (p.get().getAuctionStatus()) {
            throw new IllegalArgumentException("Product is already auctioned");
        } else {
            p.get().setAuctionStatus(true);
        }

        if (auctionRequest.getStartDate().after(auctionRequest.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        Auction auction = new Auction(auctionRequest.getAuctionName(), auctionRequest.getStartPrice(),
                auctionRequest.getMinIncrement(), auctionRequest.getStartDate(), auctionRequest.getEndDate(),
                AuctionStatus.OPEN, p.get());

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
                        auction.getCurrentBidValue(), "Winner Awarded");
            }
        }
    }


    public void deleteAuction(String auctionName) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));
        auctionRepository.delete(auction);
    }

    public void updateAuction(String auctionName, UpdateAuctionRequest updateRequest) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));

        if (Objects.nonNull(updateRequest.getAuctionName()) ||
                !updateRequest.getAuctionName().isEmpty()) {
            if (auctionRepository.existsByAuctionName(updateRequest.getAuctionName())) {
                throw new IllegalArgumentException("Auction already exists by this name.");
            } else {
                auction.setAuctionName(updateRequest.getAuctionName());
            }
        }

        if (auction.getStatus().equals(AuctionStatus.CLOSED)) {
            auctionRepository.save(auction);
            throw new IllegalArgumentException("Can't update parameters for closed Auction");
        }

        if (Objects.nonNull(updateRequest.getMinIncrement())) {
            auction.setMinIncrement(updateRequest.getMinIncrement());
        }

        if (Objects.nonNull(updateRequest.getStartDate())) {
            if (auction.getStartDate().before(new Date())) {
                throw new IllegalArgumentException("Can't update Start Date.");
            }
            auction.setStartDate(updateRequest.getStartDate());
        }

        if (Objects.nonNull(updateRequest.getEndDate())) {
            if (auction.getStartDate().after(updateRequest.getEndDate())) {
                throw new IllegalArgumentException("Can't update End Date. Before Start");
            }
            auction.setEndDate(updateRequest.getEndDate());
        }
        auctionRepository.save(auction);
    }

    public Map<String, AuctionStatus> auctionStatus() {
        Map<String, AuctionStatus> auctions = auctionRepository.findAll().stream()
                .collect(Collectors.toMap(Auction::getAuctionName, Auction::getStatus));
        return auctions;
    }

    public void closeAuction(String auctionName) {
        Auction auction = auctionRepository.findByAuctionName(auctionName)
                .orElseThrow(() -> new ResourceNotFoundException("Auction", "auctionName", auctionName));

        if (auction.getStartDate().after(new Date())) {
            auction.setStatus(AuctionStatus.CLOSED);
        } else {
            auction.setEndDate(new Date());
        }

        auctionRepository.save(auction);
    }
}
