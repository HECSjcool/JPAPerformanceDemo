package com.ecs.hermes.jpaperformance.persistence.dao;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 2/8/13
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPersonDAO {
    List<Person> saveACollectionOfPersons(List<Person> personToBeSavedList);
}
