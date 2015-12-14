package com.gateway.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Jayavardhan on 12/9/15
 */
@SuppressWarnings("unchecked")
@Transactional
public class GenericDaoImp<T> implements GenericDao<T> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public T findById(long id) throws HibernateException {
        return (T)getSession().get( getPersistentClass(), id);
    }


    @Override
    public void delete(Object object) {
        try {
            getSession().delete(object);
        }catch (IllegalArgumentException e) {

        }
        catch (NullPointerException e) {
        }
        catch(HibernateException e) {
        }
    }

    @Override
    public T save(T object) {
        if( object != null) {
            getSession().save(object);
        }
        return object;
    }

    @Override
    public T update(T object) {
        if( object != null) {
            getSession().update(object);
        }
        return object;
    }

    @Override
    public T saveOrUpdate(T object) {
        try {
            getSession().saveOrUpdate(object);
        }catch (IllegalArgumentException e) {
        }
        catch(NullPointerException e){
        }
        catch(HibernateException e) {
        }
        return object;
    }

    @Override
    public ClassMetadata getMetadata() {
        return getSessionFactory().getClassMetadata(getPersistentClass());
    }

    @Override
    public ClassMetadata getMetadata(Class cls) {
        return getSessionFactory().getClassMetadata(cls);
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(getPersistentClass());
    }

    public Criteria getCriteria(Class cls) {
        return getSession().createCriteria(cls);
    }



    public Criteria getCriteriaByNotDeleted() {
        return getCriteria().add(Restrictions.isNull("deleted"));
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Long getId(Criteria criteria) {
        criteria.setProjection(Projections.property("id"));
        return (Long)criteria.uniqueResult();
    }
}