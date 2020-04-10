package com.foobar.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.foobar.constants.ErrorCode;
import com.foobar.domain.Buyer;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BuyerDao extends BaseDao<Buyer> {
    private Map<String, Buyer> buyerData = new HashMap<String, Buyer>();

    public Buyer create(final Buyer buyer) {
        if (buyer.getIdentifier() == null) {
            buyer.setIdentifier(UUID.randomUUID().toString());
        }

        persist(buyer);
        return buyer;
    }

    public Buyer findById(final String id) throws FooBarException {
        if (!buyerData.containsKey(id)) {
            throw new FooBarException(ErrorCode.BUYER_NOT_FOUND.name(), ErrorCode.BUYER_NOT_FOUND.getMessage(), 404);
        }
        return buyerData.get(id);
    }

    public Buyer update(final String id, final Buyer buyer) throws FooBarException {
        final Buyer existingBuyer = findById(id);
        existingBuyer.merge(buyer);
        persist(existingBuyer);
        return existingBuyer;
    }

    private void persist(final Buyer existingBuyer) {
        buyerData.put(existingBuyer.getIdentifier(), existingBuyer);
    }

    public void delete(final String id) throws FooBarException {
        findById(id);
        buyerData.remove(id);
    }
}
