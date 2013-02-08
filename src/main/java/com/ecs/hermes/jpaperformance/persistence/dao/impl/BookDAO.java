package com.ecs.hermes.jpaperformance.persistence.dao.impl;

import com.ecs.hermes.jpaperformance.persistence.domain.Book;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/18/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "bookDAO")
public class BookDAO extends GenericDAOJPAImpl<Book, Long> {

}
