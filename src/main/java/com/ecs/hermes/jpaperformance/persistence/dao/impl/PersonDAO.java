package com.ecs.hermes.jpaperformance.persistence.dao.impl;

import com.ecs.hermes.jpaperformance.persistence.dao.IPersonDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "personDAO")
@Scope(value = "prototype")
public class PersonDAO extends GenericDAOJPAImpl<Person, Long> implements IPersonDAO {

    @Override
    public List<Person> saveACollectionOfPersons(List<Person> personToBeSavedList) {
        int counter = 1;
        List listSavedPersons = new ArrayList<Person>(personToBeSavedList.size());

        for (Person p : personToBeSavedList) {
            listSavedPersons.add(this.em.merge(p));
            counter++;

            if (counter % 10000 == 0) {

                this.em.flush();
                this.em.clear();
            }
        }
        return listSavedPersons;
    }

    @Override
    public Person findById(Long id) {
        return this.em.find(PersonGoodPerformance.class, id);
    }

}
