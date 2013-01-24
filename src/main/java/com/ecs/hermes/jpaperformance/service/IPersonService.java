package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/24/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IPersonService {

    @Transactional
    public Person saveAPerson(Person p);


    @Transactional
    public List<Person> saveACollectionOfPersons(List<Person> personList);


    @Transactional
    public List<Person> saveACollectionOfPersonsWithOneCallToDao(List<Person> personList);

    @Transactional
    public void retrieveInSameTransactionTwoTimesTheSamePerson(Long id);
}
