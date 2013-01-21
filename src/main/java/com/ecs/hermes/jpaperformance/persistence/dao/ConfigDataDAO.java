package com.ecs.hermes.jpaperformance.persistence.dao;

import com.ecs.hermes.jpaperformance.persistence.domain.ConfigData;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/17/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "configDAO")
public class ConfigDataDAO extends GenericDAOJPAImpl<ConfigData, Long> {
}
