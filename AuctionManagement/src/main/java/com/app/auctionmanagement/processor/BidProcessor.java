package com.app.auctionmanagement.processor;

import com.app.auctionmanagement.model.Auction;
import com.app.auctionmanagement.model.AuctionStatus;
import com.app.auctionmanagement.model.Bid;
import com.app.auctionmanagement.model.BidStatus;
import com.app.auctionmanagement.repository.AuctionRepository;
import com.app.auctionmanagement.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BidProcessor {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

    private Logger logger = Logger.getLogger(BidProcessor.class.getName());

    @Scheduled(fixedRate = 5000)
    public void process() {
        List<Auction> openAuctions = auctionRepository.findByStatus(AuctionStatus.OPEN);
        openAuctions.stream().filter(auction -> auction.getEndDate().before(new Date())).forEach(auction -> {
            logger.info("Closing Auction : " + auction);
            auction.setStatus(AuctionStatus.CLOSED);
            auctionRepository.save(auction);

            Optional<List<Bid>> findByAuction = bidRepository.findByAuction(auction);
            if (findByAuction.isPresent()) {
                List<Bid> bidList = findByAuction.get();
                Collections.sort(bidList);

                Bid winningBid = null;
                for (Bid bid : bidList) {
                    Date date = bid.getDate();
                    if (date.after(auction.getStartDate()) && date.before(auction.getEndDate())) {
                        bid.setBidStatus(BidStatus.AWARDED);
                        winningBid = bid;
                        break;
                    } else {
                        bid.setBidStatus(BidStatus.REJECTED);
                        bidRepository.save(bid);
                    }
                }

                if (Objects.nonNull(winningBid)) {
                    logger.info("Awarding auction for " + winningBid);
                    bidRepository.save(winningBid);

                    Long winnerId = winningBid.getUser().getId();
                    auction.setWinnerUserId(winnerId);
                    auction.setCurrentBidValue(winningBid.getBidValue());
                    auctionRepository.save(auction);
                }
            }

        });
    }
}
