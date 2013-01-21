package com.ecs.hermes.jpaperformance.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public interface IGenericDAO<T, ID extends Serializable> {

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T findById(final ID id);

    /**
     * Find an entity by example
     *
     * @param the example entity the primary key
     * @return the entity
     */
    List<T> findByExample(final T obj);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */

    List<T> findAll();


    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */

    T save(final T entity);

    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */

    void delete(final T entity);


    List<T> saveACollection(List<T> entitiesToSave);


}