package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.ConfigData;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/17/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "/spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestConfigService {

    Logger logger = Logger.getLogger(com.ecs.hermes.jpaperformance.service.TestConfigService.class);

    @Autowired
    IConfigDataService cs;

    @Test
    public void saveConfigDataAndGetParticularEvent() {

        List<ConfigData> configDataList = new ArrayList<ConfigData>(100);
        for (int i = 0; i < 100; i++) {
            ConfigData cf = new ConfigData();
            cf.setKey("key" + i);
            cf.setValue("value" + i);
            configDataList.add(cf);
        }
        cs.saveACollectionOfLibraries(configDataList);

        logger.info("Getting a particular config data element - this can be done through a other object that as link");

        ConfigData cd = cs.getConfigData(1000L);

        logger.info(cd);
        assertTrue("key0".equals(cd.getKey()));
        assertTrue("value0".equals(cd.getValue()));

        List<ConfigData> cdList = cs.getConfigData("key1");
        assertTrue(cdList.size() == 1);
        logger.info(cdList.get(0));

        logger.info(cs.getConfigData("key1"));


    }
}
