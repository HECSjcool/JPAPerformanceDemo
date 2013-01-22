package com.ecs.hermes.jpaperformance.persistence.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Entity

public class Library {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_GEN")
    @SequenceGenerator(
            name = "SEQ_GEN",
            sequenceName = "my_library_sequence", allocationSize = 1000)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "LIB_ID")
    // @Fetch(value = FetchMode.SELECT)
    // @BatchSize(size = 2)
    private Set<Book> bookSet;

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        if (bookSet != null ? !bookSet.equals(library.bookSet) : library.bookSet != null) return false;
        if (!name.equals(library.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (bookSet != null ? bookSet.hashCode() : 0);
        return result;
    }
}
