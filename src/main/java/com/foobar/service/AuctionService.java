package com.foobar.service;

import com.foobar.constants.AuctionStatus;
import com.foobar.constants.AuctionStrategy;
import com.foobar.constants.ErrorCode;
import com.foobar.dao.AuctionDao;
import com.foobar.domain.Auction;
import com.foobar.exception.FooBarException;
import com.foobar.service.factory.AuctionProcessorFactory;
import com.foobar.service.factory.BaseAuctionProcessor;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class AuctionService {
    private SellerService sellerService;

    private AuctionDao auctionDao = new AuctionDao();

    public AuctionService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public Auction create(final Auction auction) throws FooBarException {
        try {
            sellerService.findById(auction.getSeller().getIdentifier());
        } catch (FooBarException e) {
            throw new FooBarException(ErrorCode.SELLER_NOT_FOUND.name(), "Invalid Seller for Auction", 400);
        }
        auction.setStatus(AuctionStatus.created);
        return auctionDao.create(auction);
    }

    public Auction findById(final String id) throws FooBarException {
        return auctionDao.findById(id);
    }

    public Auction close(final String id) throws FooBarException {
        final BaseAuctionProcessor auctionProcessor = AuctionProcessorFactory.getAuctionProcessor(AuctionStrategy.highest_uniqe_bid);
        if (auctionProcessor == null) {
            throw new FooBarException(ErrorCode.INVALID_AUCTION_PROCESSOR.name(), ErrorCode.INVALID_AUCTION_PROCESSOR.getMessage(), 400);
        }
        return auctionProcessor.processAuction(id);
    }
}
