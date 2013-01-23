package com.ecs.hermes.jpaperformance.persistence.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 7/01/13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_GEN")
    @SequenceGenerator(
            name = "SEQ_GEN",
            sequenceName = "my_Book_sequence", allocationSize = 10000)
    private Long id;

    @Column
    private String title;
    @Column
    private String isdn;

    public Date getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate(Date lastReadDate) {
        this.lastReadDate = lastReadDate;
    }

    @Column
    private Date lastReadDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (isdn != null ? !isdn.equals(book.isdn) : book.isdn != null) return false;
        if (lastReadDate != null ? !lastReadDate.equals(book.lastReadDate) : book.lastReadDate != null) return false;
        return !(title != null ? !title.equals(book.title) : book.title != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (isdn != null ? isdn.hashCode() : 0);
        result = 31 * result + (lastReadDate != null ? lastReadDate.hashCode() : 0);
        return result;
    }
}
