package com.foobar.service;

import com.foobar.dao.SellerDao;
import com.foobar.domain.Seller;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class SellerService {
    private SellerDao sellerDao = new SellerDao();

    public Seller create(final Seller seller) {
        return sellerDao.create(seller);
    }

    public Seller findById(final String id) throws FooBarException {
        return sellerDao.findById(id);
    }
}
