package com.ecs.hermes.jpaperformance.persistence.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Example;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */
public class GenericDAOJPAImpl<T, ID extends Serializable> implements IGenericDAO<T, ID> {

    @PersistenceContext
    protected EntityManager em;
    private final Class<T> persistentClass;

    public GenericDAOJPAImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericDAOJPAImpl(final Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    @Override
    public T findById(ID id) {
        return this.em.find(persistentClass, id);
    }

    @Override
    public List<T> findByExample(T obj) {
        Session hibernateSession = (Session) em.getDelegate();
        Example ex = Example.create(obj);
        return hibernateSession.createCriteria(obj.getClass()).add(ex).setCacheable(true).list();

    }

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    @Override
    public List<T> findAll() {

        return em.createQuery("from " + persistentClass.getName())
                .getResultList();
    }


    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public T save(T entity) {

        final T savedEntity = this.em.merge(entity);
        return savedEntity;
    }

    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    @Override
    public void delete(T entity) {
        this.em.remove(entity);
    }

    @Override
    public List<T> saveACollection(List<T> entitiesToSave) {
        int counter = 0;
        List<T> list = new ArrayList<T>();

        for (T p : entitiesToSave) {
            list.add(this.em.merge(p));
            counter++;

            if (counter % 10000 == 0) {

                this.em.flush();
                this.em.clear();
            }
        }
        return list;
    }
}
