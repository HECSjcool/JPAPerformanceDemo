/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecs.hermes.jpaperformance.launchers;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;
import com.ecs.hermes.jpaperformance.service.IPersonService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 */

public class SampleMainForProfiling {

    private static final int NUMBER_OF_PERSONS_CREATED = 100000;

    static Logger logger = Logger.getLogger(SampleMainForProfiling.class);

    private static Person createDummyGPPerson() {
        Person p = new PersonGoodPerformance();
        setFirstAndLastNames(p);
        return p;
    }

    private static void setFirstAndLastNames(Person p) {
        p.setFirstName("first" + System.currentTimeMillis());
        p.setLastName("last" + System.currentTimeMillis());
    }

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring-dao.xml"});

        List listPersons = new ArrayList<PersonBadPerformance>(NUMBER_OF_PERSONS_CREATED);

        for (int i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = createDummyGPPerson();
            listPersons.add(p);

        }
        context.getBean(IPersonService.class).saveACollectionOfPersonsWithOneCallToDao(listPersons);
        System.exit(0);

    }

}
