package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.service.utils.PersonBatchRunnable;
import com.ecs.hermes.jpaperformance.service.utils.PersonSampleDataCreator;
import org.apache.commons.lang.exception.ExceptionUtils;
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

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "/spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPersonServiceJPA {

    static final int NUMBER_OF_PERSONS_CREATED = 100000;
    public static final int NUMB_OF_THREADS = 5;

    @Autowired
    IPersonService personService;
    long begin;
    static Logger logger = Logger.getLogger(TestPersonServiceJPA.class);


    @Before
    public void setChrono() {
        begin = System.currentTimeMillis();
    }

    @After
    public void printTime() {
        logger.info("Test took " + (System.currentTimeMillis() - begin));
    }


    @Test
    public void testCreateABPPerson() {

        Person p = PersonSampleDataCreator.createDummyBPPerson();
        p.setId(0L);
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());

    }

    @Test
    public void testCreateAGPPerson() {

        Person p = PersonSampleDataCreator.createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());
        logger.info("Person created with ID " + p2.getId());
        p2.setLastName("aaaa");

    }

    @Test
    public void testCreateTwoGPPerson() {

        Person p = PersonSampleDataCreator.createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());
        logger.info("Person created with ID " + p2.getId());

        p = PersonSampleDataCreator.createDummyGPPerson();
        p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());
        logger.info("Person created with ID " + p2.getId());

    }

    /**
     * To RUN WHILE GOING THROUGH SLIDES  ( 2 min 24)
     */
    @Test
    public void testCreateCollectionOfBPPersons() {

        for (Integer i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {

            Person p = PersonSampleDataCreator.createDummyGPPerson();
            p.setId(i.longValue() + 1);
            Person p2 = personService.saveAPerson(p);
            assertNotNull(p2.getId());
        }

    }

    /**
     * Time took : 1 m 27
     */
    @Test
    public void testCreateCollectionOfGPPersons() {

        for (Integer i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = PersonSampleDataCreator.createDummyGPPerson();
            Person p2 = personService.saveAPerson(p);
            assertNotNull(p2.getId());
        }

    }


    /**
     * Time took : 42
     */
    @Test
    public void testCreateCollectionOfPersonsInOneTransaction() {

        List listPersons = new ArrayList<Person>(NUMBER_OF_PERSONS_CREATED);

        for (int i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = PersonSampleDataCreator.createDummyGPPerson();

            listPersons.add(p);

        }
        List<Person> listPerson = personService.saveACollectionOfPersons(listPersons);

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

    @Test
    public void testCreateCollectionOfPersonsInOneTransactionAndFlushingWithMultiThreading() {

        List<PersonBatchRunnable> personBatchRunnables = new ArrayList<PersonBatchRunnable>(NUMB_OF_THREADS);

        for (int j = 0; j < NUMB_OF_THREADS; j++) {
            List listPersons = new ArrayList<PersonBadPerformance>(NUMBER_OF_PERSONS_CREATED / NUMB_OF_THREADS);

            for (int i = NUMBER_OF_PERSONS_CREATED / NUMB_OF_THREADS * j; i < NUMBER_OF_PERSONS_CREATED / NUMB_OF_THREADS * (j + 1); i++) {
                Person p = PersonSampleDataCreator.createDummyGPPerson();
                listPersons.add(p);

            }
            personBatchRunnables.add(new PersonBatchRunnable(listPersons, personService));

        }

        List<Thread> threadList = new ArrayList<Thread>(NUMB_OF_THREADS);
        int counter = 1;
        for (PersonBatchRunnable pbr : personBatchRunnables) {

            Thread t = new Thread(pbr, "ORMPerformanceThread" + counter);
            threadList.add(t);
            t.start();
            counter++;

        }
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Test
    public void testGetAPersonWithAnParticularInIDByUsingTheFirstLevelCache() {

        Person p = PersonSampleDataCreator.createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);

        personService.retrieveInSameTransactionTwoTimesTheSamePerson(p2.getId());

    }
}
