package com.ecs.hermes.jpaperformance.persistence.dao;

import com.ecs.hermes.jpaperformance.persistence.domain.Library;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "libraryDAO")
public class LibraryDAO extends GenericDAOJPAImpl<Library, Long> {


}
