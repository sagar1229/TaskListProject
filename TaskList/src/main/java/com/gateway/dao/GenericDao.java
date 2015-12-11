package com.gateway.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.metadata.ClassMetadata;

/**
 * Created by Jayavardhan on 12/9/15
 */
public interface GenericDao<T> {

    T findById(long id) throws HibernateException;

    void delete(T object);

    T save(T object);

    T update(T object);

    T saveOrUpdate(T object);

    ClassMetadata getMetadata();

    ClassMetadata getMetadata(Class cls);

    Long getId(Criteria criteria);
}