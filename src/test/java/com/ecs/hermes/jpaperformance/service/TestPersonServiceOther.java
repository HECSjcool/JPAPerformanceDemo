package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.service.utils.PersonSampleDataCreator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 2/13/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = {"/spring-dao.xml", "/spring-dao-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPersonServiceOther {

    private static final int NUMBER_OF_PERSONS_CREATED = 100000;
    @Autowired
    IPersonService personService;
    long begin;
    static Logger logger = Logger.getLogger(TestPersonServiceOther.class);


    @Before
    public void setChrono() {
        begin = System.currentTimeMillis();
    }

    @After
    public void printTime() {
        logger.info("Test took " + (System.currentTimeMillis() - begin));
    }


    @Test
    public void testCreateCollectionOfPersonsInOneTransactionAndFlushing() {

        List listPersons = new ArrayList<PersonBadPerformance>(NUMBER_OF_PERSONS_CREATED);

        for (int i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = PersonSampleDataCreator.createDummyGPPerson();
            listPersons.add(p);

        }

        personService.saveACollectionOfPersonsWithOneCallToDao(listPersons);

    }
}
