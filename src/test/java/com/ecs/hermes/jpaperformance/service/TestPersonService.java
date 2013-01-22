package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.dao.PersonDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;
import com.ecs.hermes.jpaperformance.persistence.launchers.SpringUtils;
import org.apache.log4j.Logger;
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

    public static final int NUMBER_OF_PERSONS_CREATED = 10000;
    static PersonService personService;
    static ApplicationContext context = SpringUtils.init();
    static PersonDAO personDAO;
    Logger logger = Logger.getLogger(TestPersonService.class);

    @BeforeClass
    public static void setUp() {

        personService = (PersonService) context.getBean("personService");
        personDAO = (PersonDAO) context.getBean("personDAO");

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

    @Test
    public void testCreateCollectionOfBPPersons() {

        for (Integer i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = createDummyBPPerson();
            p.setId(i.longValue() + 1);
            Person p2 = personService.saveAPerson(p);
            assertNotNull(p2.getId());
        }

    }

    @Test
    public void testCreateCollectionOfGPPersons() {

        for (Integer i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = createDummyGPPerson();
            Person p2 = personService.saveAPerson(p);
            assertNotNull(p2.getId());
        }

    }

    @Test
    public void testCreateCollectionOfPersonsInOneTransaction() {

        List listPersons = new ArrayList<Person>(NUMBER_OF_PERSONS_CREATED);

        for (int i = 0; i < NUMBER_OF_PERSONS_CREATED; i++) {
            Person p = createDummyGPPerson();

            listPersons.add(p);

        }
        personService.saveACollectionOfPersons(listPersons);

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
    public void testGetAPersonWithAnParticularInIDByUsingTheFirstLevelCache() {
        Person p = createDummyGPPerson();
        Person p2 = personService.saveAPerson(p);

        personService.retrieveInSameTransactionTwoTimesTheSamePerson(p2.getId());

    }
}
