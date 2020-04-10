package com.foobar.controller;

import com.foobar.domain.Bid;
import com.foobar.exception.FooBarException;
import com.foobar.service.BidService;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BidController {
    private BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    public Bid create(final Bid bid) throws FooBarException {
        return bidService.create(bid);
    }

    public Bid findById(final String id) throws FooBarException {
        return bidService.findById(id);
    }

    public Bid update(final String id, final Bid bid) throws FooBarException {
        return bidService.update(id, bid);
    }

    public Bid withdraw(final String id) throws FooBarException {
        return bidService.withdraw(id);
    }
}
