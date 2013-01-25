/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecs.hermes.jpaperformance.launchers;

import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonBadPerformance;
import com.ecs.hermes.jpaperformance.persistence.domain.PersonGoodPerformance;
import com.ecs.hermes.jpaperformance.service.IPersonService;
import com.ecs.hermes.jpaperformance.service.utils.PersonBatchRunnable;
import com.ecs.hermes.jpaperformance.utils.SpringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 */
public class SampleMainForProfiling {

    private static final int NUMBER_OF_PERSONS_CREATED = 100000;
    static ApplicationContext context = SpringUtils.init();
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
        System.exit(0);

    }

}
