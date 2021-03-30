package com.app.auctionmanagement.repository;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByAuctionProduct(Long productId);

    Optional<List<Bid>> findByAuction(Auction i);
}
