package com.foobar.service;

import java.util.List;
import com.foobar.constants.BidStatus;
import com.foobar.constants.ErrorCode;
import com.foobar.dao.BidDao;
import com.foobar.domain.Auction;
import com.foobar.domain.Bid;
import com.foobar.domain.Buyer;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BidService {
    private AuctionService auctionService;

    private BuyerService buyerService;

    private BidDao bidDao = new BidDao();

    public BidService(AuctionService auctionService, BuyerService buyerService) {
        this.auctionService = auctionService;
        this.buyerService = buyerService;
    }

    public Bid create(final Bid bid) throws FooBarException {
        final Auction auction;
        try {
            auction = auctionService.findById(bid.getAuction().getIdentifier());
        } catch (FooBarException e) {
            throw new FooBarException(ErrorCode.SELLER_NOT_FOUND.name(), "Invalid Auction to Bid", 400);
        }
        final Buyer buyer;
        try {
            buyer = buyerService.findById(bid.getBuyer().getIdentifier());
        } catch (FooBarException e) {
            throw new FooBarException(ErrorCode.SELLER_NOT_FOUND.name(), "Invalid Buyer to Bid", 400);
        }

        if (bidDao.getBidByAuctionAndBuyer(auction, bid.getBuyer()).isPresent()) {
            throw new FooBarException(ErrorCode.BID_ALREADY_PRESENT.name(), ErrorCode.BID_ALREADY_PRESENT.getMessage(), 400);
        }

        if (bid.getBidAmount() > auction.getMaxBidAmount() || bid.getBidAmount() < auction.getMinBidAmount()) {
            throw new FooBarException(ErrorCode.INVALID_BID_AMOUNT.name(), ErrorCode.INVALID_BID_AMOUNT.getMessage(), 400);
        }
        bid.setStatus(BidStatus.created);

        buyer.setAuctionsCount(buyer.getAuctionsCount() + 1);
        return bidDao.create(bid);
    }

    public Bid findById(final String id) throws FooBarException {
        return bidDao.findById(id);
    }

    public Bid update(final String id, final Bid bid) throws FooBarException {
        final Auction auction = bid.getAuction();
        if (bid.getBidAmount() > auction.getMaxBidAmount() || bid.getBidAmount() < auction.getMinBidAmount()) {
            throw new FooBarException(ErrorCode.INVALID_BID_AMOUNT.name(), ErrorCode.INVALID_BID_AMOUNT.getMessage(), 400);
        }

        final Bid existingBid = findById(id);
        existingBid.merge(bid);
        bidDao.update(id, existingBid);
        return existingBid;
    }

    public Bid withdraw(final String id) throws FooBarException {
        final Bid bid = findById(id);
        bid.setStatus(BidStatus.withdrawn);
        bidDao.update(id, bid);

        final Buyer buyer = bid.getBuyer();
        buyer.setAuctionsCount(buyer.getAuctionsCount() > 0 ? buyer.getAuctionsCount() - 1 : 0);
        return bid;
    }

    public Bid reject(final String id) throws FooBarException {
        final Bid bid = findById(id);
        bid.setStatus(BidStatus.rejected);
        bidDao.update(id, bid);
        return bid;
    }

    public Bid accept(final String id) throws FooBarException {
        final Bid bid = findById(id);
        bid.setStatus(BidStatus.accepted);
        bidDao.update(id, bid);
        return bid;
    }

    public List<Bid> getAuctionBids(final String auctionId, final BidStatus bidStatus) {
        return bidDao.getAuctionBids(auctionId, bidStatus);
    }
}
