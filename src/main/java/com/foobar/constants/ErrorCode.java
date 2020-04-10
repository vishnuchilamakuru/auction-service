package com.foobar.constants;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public enum ErrorCode {
    SELLER_NOT_FOUND("Seller Not Found"),
    BUYER_NOT_FOUND("Buyer Not Found"),
    AUCTION_NOT_FOUND("Auction not Found"),
    BID_NOT_FOUND("Bid Not Found"),
    INVALID_BID_AMOUNT("Invalid Bid Amount"),
    BID_ALREADY_PRESENT("Bid Already Present"),
    INVALID_AUCTION_PROCESSOR("Invalid Auction Processor");

    public String getMessage() {
        return message;
    }

    private String message;

    ErrorCode(final String message) {
        this.message = message;
    }
}
