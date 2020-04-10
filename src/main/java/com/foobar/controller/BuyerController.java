package com.foobar.controller;

import com.foobar.domain.Buyer;
import com.foobar.exception.FooBarException;
import com.foobar.service.BuyerService;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BuyerController {
    private BuyerService buyerService;

    public BuyerController(final BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    public Buyer createBuyer(final Buyer buyer) {
        return buyerService.create(buyer);
    }

    public Buyer findById(final String id) throws FooBarException {
        return buyerService.findById(id);
    }
}
