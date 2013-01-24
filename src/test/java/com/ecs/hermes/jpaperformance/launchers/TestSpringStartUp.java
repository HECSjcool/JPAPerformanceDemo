package com.ecs.hermes.jpaperformance.launchers;

import com.ecs.hermes.jpaperformance.utils.SpringUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class TestSpringStartUp {

    @Test
    public void testLoadingOfXmlFile() {

        SpringUtils.init();
    }
}
