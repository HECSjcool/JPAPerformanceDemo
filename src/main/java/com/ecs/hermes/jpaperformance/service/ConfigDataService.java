package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.ConfigData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/17/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "configDataService")
public class ConfigDataService {

    @Autowired
    @Qualifier("configDAO")
    private IGenericDAO genericDAO;

    @Transactional
    public List<ConfigData> saveACollectionOfLibraries(List<ConfigData> configDataList) {
        return genericDAO.saveACollection(configDataList);

    }

    @Transactional
    public List<ConfigData> getAllData() {

        return genericDAO.findAll();
    }

    @Transactional
    public ConfigData getConfigData(Long id) {

        return (ConfigData) genericDAO.findById(id);
    }

    @Transactional
    public List<ConfigData> getConfigData(String key) {

        ConfigData exampleConfigData = new ConfigData();
        exampleConfigData.setKey(key);
        return genericDAO.findByExample(exampleConfigData);
    }
}
