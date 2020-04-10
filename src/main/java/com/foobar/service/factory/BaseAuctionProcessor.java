package com.foobar.service.factory;

import com.foobar.constants.AuctionStrategy;
import com.foobar.domain.Auction;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public abstract class BaseAuctionProcessor<T extends AuctionStrategy> {
    private T auctionStrategy;

    public BaseAuctionProcessor(T auctionStrategy) {
        this.auctionStrategy = auctionStrategy;
    }

    public T getAuctionStrategy() {
        return auctionStrategy;
    }

    public abstract Auction processAuction(final String id) throws FooBarException;
}
