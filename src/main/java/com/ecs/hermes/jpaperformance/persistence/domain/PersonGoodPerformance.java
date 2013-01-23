package com.ecs.hermes.jpaperformance.persistence.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 4/01/13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PersonGoodPerformance extends Person {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_GEN")
    @SequenceGenerator(
            name = "SEQ_GEN",
            sequenceName = "my_sequence", allocationSize = 100)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
