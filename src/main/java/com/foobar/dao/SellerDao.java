package com.foobar.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.foobar.constants.ErrorCode;
import com.foobar.domain.Seller;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class SellerDao extends BaseDao<Seller> {
    private Map<String, Seller> sellerData = new HashMap<String, Seller>();

    public Seller create(final Seller seller) {
        if (seller.getIdentifier() == null) {
            seller.setIdentifier(UUID.randomUUID().toString());
        }

        persist(seller);
        return seller;
    }

    public Seller findById(final String id) throws FooBarException {
        if (!sellerData.containsKey(id)) {
            throw new FooBarException(ErrorCode.SELLER_NOT_FOUND.name(), ErrorCode.SELLER_NOT_FOUND.getMessage(), 404);
        }
        return sellerData.get(id);
    }

    public Seller update(final String id, final Seller seller) throws FooBarException {
        final Seller existingSeller = findById(id);
        existingSeller.merge(seller);
        persist(existingSeller);
        return existingSeller;
    }

    private void persist(final Seller existingSeller) {
        sellerData.put(existingSeller.getIdentifier(), existingSeller);
    }

    public void delete(final String id) throws FooBarException {
        findById(id);
        sellerData.remove(id);
    }
}
