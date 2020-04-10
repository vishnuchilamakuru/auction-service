package com.foobar.dao;

import com.foobar.domain.BaseDomain;
import com.foobar.exception.FooBarException;

/**
 * @author vishnu chilamakuru
 * @since 10-Apr-2020
 */
public abstract class BaseDao<T extends BaseDomain> {
    public abstract T create(T t);

    public abstract T findById(String id) throws FooBarException;

    public abstract T update(String id, T t) throws FooBarException;

    public abstract void delete(String id) throws FooBarException;

}
