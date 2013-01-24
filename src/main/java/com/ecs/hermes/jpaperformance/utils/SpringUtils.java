package com.ecs.hermes.jpaperformance.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class SpringUtils {
    static ApplicationContext context =
            new ClassPathXmlApplicationContext(new String[]{"spring-dao.xml"});

    private SpringUtils() {
    }

    public static ApplicationContext init() {

        return context;

    }
}
