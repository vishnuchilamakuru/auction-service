package com.foobar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import com.foobar.constants.BidStatus;
import com.foobar.constants.ErrorCode;
import com.foobar.domain.Auction;
import com.foobar.domain.Bid;
import com.foobar.domain.Buyer;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BidDao extends BaseDao<Bid> {
    private Map<String, Bid> bidData = new HashMap<String, Bid>();

    public Bid create(final Bid bid) {
        if (bid.getIdentifier() == null) {
            bid.setIdentifier(UUID.randomUUID().toString());
        }

        persist(bid);
        return bid;
    }

    public Bid findById(final String id) throws FooBarException {
        if (!bidData.containsKey(id)) {
            throw new FooBarException(ErrorCode.BID_NOT_FOUND.name(), ErrorCode.BID_NOT_FOUND.getMessage(), 404);
        }
        return bidData.get(id);
    }

    public Bid update(final String id, final Bid bid) throws FooBarException {
        final Bid existingBid = findById(id);
        existingBid.merge(bid);
        persist(existingBid);
        return existingBid;
    }

    private void persist(final Bid existingBid) {
        bidData.put(existingBid.getIdentifier(), existingBid);
    }

    public void delete(final String id) throws FooBarException {
        findById(id);
        bidData.remove(id);
    }

    public Optional<Bid> getBidByAuctionAndBuyer(final Auction auction, final Buyer buyer) {
        final List<Bid> bids = new ArrayList<>();
        bidData.forEach((id, bid) -> {
            if (bid.getAuction().equals(auction) && bid.getBuyer().equals(buyer)) {
                bids.add(bid);
            }
        });

        return bids.isEmpty() ? Optional.empty() : Optional.ofNullable(bids.get(0));
    }

    public List<Bid> getAuctionBids(final String auctionid, final BidStatus bidStatus) {
        final List<Bid> bids = new ArrayList<>();
        bidData.forEach((id, bid) -> {
            if (bid.getAuction().getIdentifier().equals(auctionid) && bid.getStatus().equals(bidStatus)) {
                bids.add(bid);
            }
        });
        return bids;
    }
}
