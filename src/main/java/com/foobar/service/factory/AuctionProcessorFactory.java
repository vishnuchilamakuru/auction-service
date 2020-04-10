package com.foobar.service.factory;

import java.util.HashMap;
import com.foobar.constants.AuctionStrategy;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class AuctionProcessorFactory {
    private static HashMap<AuctionStrategy, BaseAuctionProcessor> strategyMap = new HashMap<>();

    public static void register(final BaseAuctionProcessor auctionProcessor) {
        strategyMap.put(auctionProcessor.getAuctionStrategy(), auctionProcessor);
    }

    public static BaseAuctionProcessor getAuctionProcessor(final AuctionStrategy auctionStrategy) {
        return strategyMap.get(auctionStrategy);
    }
}
