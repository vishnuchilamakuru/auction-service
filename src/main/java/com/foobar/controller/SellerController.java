package com.foobar.controller;

import com.foobar.domain.Seller;
import com.foobar.exception.FooBarException;
import com.foobar.service.SellerService;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class SellerController {
    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public Seller createSeller(final Seller seller) {
        return sellerService.create(seller);
    }

    public Seller findById(final String id) throws FooBarException {
        return sellerService.findById(id);
    }
}
