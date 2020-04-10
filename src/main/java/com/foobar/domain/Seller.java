package com.foobar.domain;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class Seller extends BaseDomain {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void merge(final Seller seller) {
        if (seller.getName() != null) {
            this.name = seller.getName();
        }
    }

    @Override
    public String toString() {
        return "Seller{" + "name='" + name + '\'' + '}';
    }
}
