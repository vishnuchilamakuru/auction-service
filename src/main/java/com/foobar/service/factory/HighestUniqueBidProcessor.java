package com.foobar.service.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.foobar.constants.AuctionStatus;
import com.foobar.constants.AuctionStrategy;
import com.foobar.constants.BidStatus;
import com.foobar.domain.Auction;
import com.foobar.domain.Bid;
import com.foobar.exception.FooBarException;
import com.foobar.service.AuctionService;
import com.foobar.service.BidService;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class HighestUniqueBidProcessor extends BaseAuctionProcessor<AuctionStrategy> {
    private AuctionService auctionService;

    private BidService bidService;

    public HighestUniqueBidProcessor(final AuctionService auctionService, final BidService bidService) {
        super(AuctionStrategy.highest_uniqe_bid);
        this.auctionService = auctionService;
        this.bidService = bidService;
    }

    @Override
    public Auction processAuction(final String id) throws FooBarException {
        final Auction auction = auctionService.findById(id);
        final List<Bid> bids = bidService.getAuctionBids(auction.getIdentifier(), BidStatus.created);

        final Map<Double, List<Double>> bidsFrequencyByAmount = getBidsByAmount(bids);
        final Double maxBidValue = getMaxBidValue(bidsFrequencyByAmount);
        final List<Bid> maxBids = bids.stream().filter(bid -> bid.getBidAmount().equals(maxBidValue)).collect(Collectors.toList());
        final Bid winningBid = Collections.max(maxBids, Comparator.comparing(b -> b.getBuyer().getAuctionsCount()));

        final Double totalBidAmount = bids.stream().mapToDouble(b -> b.getBidAmount()).sum();
        final Bid maxBid = Collections.max(bids, Comparator.comparing(b -> b.getBidAmount()));
        final Bid minBid = Collections.min(bids, Comparator.comparing(b -> b.getBidAmount()));
        final Double avgBid = (maxBid.getBidAmount() + minBid.getBidAmount()) / 2;
        final Double sellerAmount = winningBid.getBidAmount() + (0.2 * totalBidAmount) - avgBid;
        final Double fooBarCommissionAmount = (0.8 * totalBidAmount);

        auction.setFooBarAmount(fooBarCommissionAmount);
        auction.setSellerAmount(sellerAmount);

        auction.setStatus(AuctionStatus.closed);

        updateBidsStatus(bids, winningBid);

        return auction;
    }

    private void updateBidsStatus(final List<Bid> bids, final Bid maxBid) {
        bids.stream().filter(b -> !b.getIdentifier().equals(maxBid.getIdentifier())).forEach(b -> {
            try {
                bidService.reject(b.getIdentifier());
            } catch (FooBarException e) {
                e.printStackTrace();
            }
        });

        bids.stream().filter(b -> b.getIdentifier().equals(maxBid.getIdentifier())).forEach(b -> {
            try {
                bidService.accept(b.getIdentifier());
            } catch (FooBarException e) {
                e.printStackTrace();
            }
        });
    }

    private Double getMaxBidValue(Map<Double, List<Double>> bidsFrequencyByAmount) {
        Integer minCount = Integer.MAX_VALUE;
        for (Double key : bidsFrequencyByAmount.keySet()) {
            final List<Double> values = bidsFrequencyByAmount.get(key);
            if (values.size() < minCount) {
                minCount = values.size();
            }
        }

        Double maxBidValue = 0d;
        for (Double key : bidsFrequencyByAmount.keySet()) {
            final List<Double> values = bidsFrequencyByAmount.get(key);
            if (minCount == values.size()) {
                maxBidValue = Math.max(maxBidValue, Collections.max(values));
            }
        }
        return maxBidValue;
    }

    private Map<Double, List<Double>> getBidsByAmount(List<Bid> bids) {
        final Map<Double, List<Double>> bidsFrequencyByAmount = new HashMap<>();
        bids.forEach(bid -> {
            if (!bidsFrequencyByAmount.containsKey(bid.getBidAmount())) {
                bidsFrequencyByAmount.put(bid.getBidAmount(), new ArrayList<>());
            }
            final List<Double> existingValues = bidsFrequencyByAmount.get(bid.getBidAmount());
            existingValues.add(bid.getBidAmount());
            bidsFrequencyByAmount.put(bid.getBidAmount(), existingValues);

        });
        return bidsFrequencyByAmount;
    }
}
