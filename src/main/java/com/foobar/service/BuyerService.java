package com.foobar.service;

import com.foobar.dao.BuyerDao;
import com.foobar.domain.Buyer;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BuyerService {
    private BuyerDao buyerDao = new BuyerDao();

    public Buyer create(final Buyer buyer) {
        return buyerDao.create(buyer);
    }

    public Buyer findById(final String id) throws FooBarException {
        return buyerDao.findById(id);
    }
}
