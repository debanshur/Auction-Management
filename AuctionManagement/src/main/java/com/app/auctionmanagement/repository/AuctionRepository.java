package com.app.auctionmanagement.repository;

import java.util.List;
import java.util.Optional;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

	List<Auction> findByStatus(AuctionStatus open);

	Optional<Auction> findByAuctionName(String auctionName);

}
