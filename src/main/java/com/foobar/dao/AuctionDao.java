package com.foobar.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.foobar.constants.ErrorCode;
import com.foobar.domain.Auction;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class AuctionDao extends BaseDao<Auction> {
    private Map<String, Auction> auctionData = new HashMap<String, Auction>();

    public Auction create(final Auction auction) {
        if (auction.getIdentifier() == null) {
            auction.setIdentifier(UUID.randomUUID().toString());
        }

        persist(auction);
        return auction;
    }

    public Auction findById(final String id) throws FooBarException {
        if (!auctionData.containsKey(id)) {
            throw new FooBarException(ErrorCode.AUCTION_NOT_FOUND.name(), ErrorCode.AUCTION_NOT_FOUND.getMessage(), 404);
        }
        return auctionData.get(id);
    }

    public Auction update(final String id, final Auction auction) throws FooBarException {
        final Auction existingAuction = findById(id);
        existingAuction.merge(auction);
        persist(existingAuction);
        return existingAuction;
    }

    private void persist(final Auction existingAuction) {
        auctionData.put(existingAuction.getIdentifier(), existingAuction);
    }

    public void delete(final String id) throws FooBarException {
        findById(id);
        auctionData.remove(id);
    }

}
