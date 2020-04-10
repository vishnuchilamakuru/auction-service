package com.foobar.controller;

import com.foobar.domain.Auction;
import com.foobar.exception.FooBarException;
import com.foobar.service.AuctionService;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class AuctionController {
    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public Auction create(final Auction auction) throws FooBarException {
        return auctionService.create(auction);
    }

    public Auction findById(final String id) throws FooBarException {
        return auctionService.findById(id);
    }

    public Auction close(final String id) throws FooBarException {
        return auctionService.close(id);
    }
}
