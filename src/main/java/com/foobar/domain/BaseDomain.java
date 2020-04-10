package com.foobar.domain;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public class BaseDomain {
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "BaseDomain{" + "identifier='" + identifier + '\'' + '}';
    }
}
