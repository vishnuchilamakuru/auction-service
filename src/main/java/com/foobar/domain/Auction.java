package com.foobar.domain;

import com.foobar.constants.AuctionStatus;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class Auction extends BaseDomain {
    private String name;

    private Seller seller;

    private Double minBidAmount;

    private Double maxBidAmount;

    private AuctionStatus status;

    private Double sellerAmount;

    private Double fooBarAmount;

    public Double getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(Double sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public Double getFooBarAmount() {
        return fooBarAmount;
    }

    public void setFooBarAmount(Double fooBarAmount) {
        this.fooBarAmount = fooBarAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getMinBidAmount() {
        return minBidAmount;
    }

    public void setMinBidAmount(Double minBidAmount) {
        this.minBidAmount = minBidAmount;
    }

    public Double getMaxBidAmount() {
        return maxBidAmount;
    }

    public void setMaxBidAmount(Double maxBidAmount) {
        this.maxBidAmount = maxBidAmount;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public void merge(final Auction auction) {
        if (auction.getStatus() != null) {
            this.status = auction.getStatus();
        }

        if (auction.getMaxBidAmount() != null) {
            this.maxBidAmount = auction.getMaxBidAmount();
        }

        if (auction.getMinBidAmount() != null) {
            this.minBidAmount = auction.getMinBidAmount();
        }

        if (auction.getName() != null) {
            this.name = auction.getName();
        }
    }

    @Override
    public String toString() {
        return "Auction{" + "name='" + name + '\'' + ", seller=" + seller + ", minBidAmount=" + minBidAmount + ", maxBidAmount=" + maxBidAmount + ", status=" + status + ", sellerAmount=" + sellerAmount + ", fooBarAmount=" + fooBarAmount + '}';
    }
}
