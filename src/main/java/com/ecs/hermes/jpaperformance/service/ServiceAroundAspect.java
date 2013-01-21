package com.ecs.hermes.jpaperformance.service;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;


public class ServiceAroundAspect {

    Logger logger = Logger.getLogger(this.getClass());


    @Around("within(com.ecs.hermes.jpaperformance.service..*)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {

        // start stopwatch
        logger.info("Service call started");
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        // stop stopwatch
        long end = System.currentTimeMillis();
        logger.info("Service call took :" + (end - start) + " ms");
        return retVal;
    }

}
