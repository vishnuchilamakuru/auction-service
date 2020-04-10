package com.foobar.domain;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class Buyer extends BaseDomain {
    private String name;

    private Integer auctionsCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void merge(final Buyer buyer) {
        if (buyer.getName() != null) {
            this.name = buyer.getName();
        }
    }

    public Integer getAuctionsCount() {
        return auctionsCount;
    }

    public void setAuctionsCount(Integer auctionsCount) {
        this.auctionsCount = auctionsCount;
    }

    @Override
    public String toString() {
        return "Buyer{" + "name='" + name + '\'' + ", auctionsCount=" + auctionsCount + '}';
    }
}
