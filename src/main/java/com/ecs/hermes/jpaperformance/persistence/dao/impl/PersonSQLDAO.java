package com.ecs.hermes.jpaperformance.persistence.dao.impl;

import com.ecs.hermes.jpaperformance.persistence.dao.IGenericDAO;
import com.ecs.hermes.jpaperformance.persistence.dao.IPersonDAO;
import com.ecs.hermes.jpaperformance.persistence.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 2/8/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "personSQLDAO")
@Scope(value = "prototype")
public class PersonSQLDAO implements IPersonDAO, IGenericDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> saveACollectionOfPersons(final List<Person> personToBeSavedList) {

        Integer value = this.jdbcTemplate.queryForInt("select MY_BOOK_SEQUENCE.nextval from dual");
        value--;
        value = value * 100000;

        for (Person person : personToBeSavedList) {
            person.setId(value.longValue());
            value++;

        }

        this.jdbcTemplate.batchUpdate("INSERT INTO PERSONGOODPERFORMANCE(ID,FIRSTNAME,LASTNAME) VALUES (?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        Person person = personToBeSavedList.get(i);
                        ps.setString(1, (person.getId().toString()));
                        ps.setString(2, person.getFirstName());
                        ps.setString(3, person.getLastName());
                    }

                    @Override
                    public int getBatchSize() {
                        return personToBeSavedList.size();
                    }
                });
        return personToBeSavedList;
    }

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    @Override
    public Object findById(Serializable serializable) {
        if (true) throw new NoSuchMethodError();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Find an entity by example
     *
     * @param the example entity the primary key
     * @return the entity
     */
    @Override
    public List findByExample(Object obj) {
        if (true) throw new NoSuchMethodError();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    @Override
    public List findAll() {
        if (true) throw new NoSuchMethodError();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public Object save(Object entity) {
        if (true) throw new NoSuchMethodError();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * delete an entity from the database.
     *
     * @param entity the entity to delete
     */
    @Override
    public void delete(Object entity) {
        if (true) throw new NoSuchMethodError();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List saveACollection(List entitiesToSave) {
        if (true) throw new NoSuchMethodError();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
