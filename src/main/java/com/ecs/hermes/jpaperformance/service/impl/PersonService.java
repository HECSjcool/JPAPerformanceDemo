package com.ecs.hermes.jpaperformance.service.impl;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.dao.PersonDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.service.IPersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */

@Service(value = "personService")
@Scope(value = "prototype")
public class PersonService implements IPersonService {

    private Logger logger = Logger.getLogger(PersonService.class);

    @Autowired
    @Qualifier("personDAO")
    private IGenericDAO genericDAO;

    @Transactional
    public Person saveAPerson(Person p) {

        return (Person) genericDAO.save(p);
    }

    @Transactional
    public List<Person> saveACollectionOfPersons(List<Person> personList) {

        List listSavedPersons = new ArrayList<Person>(personList.size());

        for (Person p : personList) {
            Person pnew = (Person) genericDAO.save(p);
            listSavedPersons.add(pnew);
        }
        return listSavedPersons;
    }

    @Transactional
    public List<Person> saveACollectionOfPersonsWithOneCallToDao(List<Person> personList) {

        logger.info("Passing call to dao");

        return ((PersonDAO) genericDAO).saveACollectionOfPersons(personList);

    }

    @Transactional
    public void retrieveInSameTransactionTwoTimesTheSamePerson(Long id) {

        logger.info("Retrieving for the first time");
        genericDAO.findById(id);
        logger.info("Retrieving for the second time");
        genericDAO.findById(id);

    }

}
