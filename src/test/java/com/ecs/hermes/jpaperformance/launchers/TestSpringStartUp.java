package com.ecs.hermes.jpaperformance.launchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "/spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSpringStartUp {

    @Test
    public void testLoadingOfXmlFile() {

        //
    }
}
