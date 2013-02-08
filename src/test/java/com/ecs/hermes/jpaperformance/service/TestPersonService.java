package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;
import com.ecs.hermes.jpaperformance.service.utils.PersonBatchRunnable;
import com.ecs.hermes.jpaperformance.utils.SpringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

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
public class TestPersonService {

    static final int NUMBER_OF_PERSONS_CREATED = 100000;
    static IPersonService personService;
    static ApplicationContext context = SpringUtils.init();
    long begin;
    static Logger logger = Logger.getLogger(TestPersonService.class);

    @BeforeClass
    public static void setUp() {

        personService = (IPersonService) context.getBean("personService");
    }

    @Before
    public void setChrono() {
        begin = System.currentTimeMillis();
    }

    @After
    public void printTime() {
        logger.info("Test took " + (System.currentTimeMillis() - begin));
    }


    private Person createDummyBPPerson() {
        Person p = new PersonBadPerformance();

        setFirstAndLastNames(p);
        return p;
    }

    private Person createDummyGPPerson() {
        Person p = new PersonGoodPerformance();
        setFirstAndLastNames(p);
        return p;
    }

    private static void setFirstAndLastNames(Person p) {
        p.setFirstName("first" + System.currentTimeMillis());
        p.setLastName("last" + System.currentTimeMillis());
    }

    @Test
    public void testCreateABPPerson() {

        Person p = createDummyBPPerson();
        p.setId(0L);
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());

    }

    @Test
    public void testCreateAGPPerson() {

        Person p = createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());
        logger.info("Person created with ID " + p2.getId());

    }

    @Test
    public void testCreateTwoAGPPerson() {

        Person p = createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);
        assertNotNull(p2.getId());
        logger.info("Person created with ID " + p2.getId());

        p = createDummyGPPerson();
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

            Person p = createDummyBPPerson();
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
            Person p = createDummyGPPerson();
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
            Person p = createDummyGPPerson();

            listPersons.add(p);

        }
        List<Person> listPerson = personService.saveACollectionOfPersons(listPersons);

    }

    @Test
    public void testCreateCollectionOfPersonsInOneTransactionAndFlushing() {

        List listPersons = new ArrayList<PersonBadPerformance>(NUMBER_OF_PERSONS_CREATED);

        for (int i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = createDummyGPPerson();
            listPersons.add(p);

        }
        personService.saveACollectionOfPersonsWithOneCallToDao(listPersons);

    }

    @Test
    public void testCreateCollectionOfPersonsInOneTransactionAndFlushingWithMultiThreading() {

        List<PersonBatchRunnable> personBatchRunnables = new ArrayList<PersonBatchRunnable>(5);

        for (int j = 0; j < 5; j++) {
            List listPersons = new ArrayList<PersonBadPerformance>(NUMBER_OF_PERSONS_CREATED / 5);

            for (int i = NUMBER_OF_PERSONS_CREATED / 5 * j; i < NUMBER_OF_PERSONS_CREATED / 5 * (j + 1); i++) {
                Person p = createDummyGPPerson();
                listPersons.add(p);

            }
            personBatchRunnables.add(new PersonBatchRunnable(listPersons, (IPersonService) context.getBean("personService")));

        }

        List<Thread> threadList = new ArrayList<Thread>(5);
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

        Person p = createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);

        personService.retrieveInSameTransactionTwoTimesTheSamePerson(p2.getId());

    }
}
