package com.foobar.domain;

import com.foobar.constants.BidStatus;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class Bid extends BaseDomain {
    private Auction auction;

    private Buyer buyer;

    private Double bidAmount;

    private BidStatus status;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public BidStatus getStatus() {
        return status;
    }

    public void setStatus(BidStatus status) {
        this.status = status;
    }

    public void merge(final Bid bid) {
        if (bid.getBidAmount() != null) {
            this.bidAmount = bid.getBidAmount();
        }

        if (bid.getStatus() != null) {
            this.status = bid.getStatus();
        }
    }

    @Override
    public String toString() {
        return "Bid{" + "auction=" + auction + ", buyer=" + buyer + ", bidAmount=" + bidAmount + ", status=" + status + '}';
    }
}
