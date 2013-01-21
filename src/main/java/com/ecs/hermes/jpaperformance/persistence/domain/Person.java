package com.ecs.hermes.jpaperformance.persistence.domain;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */


public abstract class Person {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String getFirstName();

    public abstract void setFirstName(String firstName);

    public abstract String getLastName();

    public abstract void setLastName(String lastName);

}
