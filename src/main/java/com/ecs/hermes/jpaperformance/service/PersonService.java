package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.dao.PersonDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PersonService {

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

        return ((PersonDAO) genericDAO).saveACollectionOfPersons(personList);

    }

    @Transactional
    public void retrieveInSameTransactionTwoTimesTheSamePerson(Long id) {
        genericDAO.findById(id);
        genericDAO.findById(id);

    }

}
