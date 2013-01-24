package com.ecs.hermes.jpaperformance.persistence.dao;

import com.ecs.hermes.jpaperformance.persistence.domain.Library;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "libraryDAO")
public class LibraryDAO extends GenericDAOJPAImpl<Library, Long> {

    public List<Library> findAll() {

        Session sess = (Session) em.getDelegate();
        return sess.createCriteria(Library.class).setMaxResults(100).list();

    }

      /* CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Library> c= cb.createQuery(Library.class);
        Root<Library> lib=c.from(Library.class);
        c.select(lib);
        return em.createQuery(c).getResultList(); */
}
