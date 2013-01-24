package com.ecs.hermes.jpaperformance.service;

import com.ecs.hermes.jpaperformance.persistence.domain.ConfigData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/24/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IConfigDataService {
    @Transactional
    List<ConfigData> saveACollectionOfLibraries(List<ConfigData> configDataList);

    @Transactional
    List<ConfigData> getAllData();

    @Transactional
    ConfigData getConfigData(Long id);

    @Transactional
    List<ConfigData> getConfigData(String key);
}
