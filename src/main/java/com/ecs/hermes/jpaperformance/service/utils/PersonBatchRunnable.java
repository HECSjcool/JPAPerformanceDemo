package com.ecs.hermes.jpaperformance.service.utils;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.service.IPersonService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/23/13
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonBatchRunnable implements Runnable {

    private final List<Person> personList;
    private final IPersonService personService;

    public PersonBatchRunnable(List<Person> personsToSave, IPersonService personService) {
        personList = personsToSave;
        this.personService = personService;
    }

    @Override
    public void run() {
        personService.saveACollectionOfPersonsWithOneCallToDao(personList);
    }
}
