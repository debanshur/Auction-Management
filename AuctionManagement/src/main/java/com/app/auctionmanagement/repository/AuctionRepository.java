package com.app.auctionmanagement.repository;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findByStatus(AuctionStatus open);

    Optional<Auction> findByAuctionName(String auctionName);

    Boolean existsByAuctionName(String auctionName);
}

