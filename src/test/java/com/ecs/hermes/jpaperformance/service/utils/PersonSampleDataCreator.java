package com.ecs.hermes.jpaperformance.service.utils;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 2/13/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonSampleDataCreator {

    public static Person createDummyBPPerson() {
        Person p = new PersonBadPerformance();

        setFirstAndLastNames(p);
        return p;
    }

    public static Person createDummyGPPerson() {
        Person p = new PersonGoodPerformance();
        setFirstAndLastNames(p);
        return p;
    }

    private static void setFirstAndLastNames(Person p) {
        p.setFirstName("first" + System.currentTimeMillis());
        p.setLastName("last" + System.currentTimeMillis());
    }
}
