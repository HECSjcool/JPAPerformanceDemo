package com.ecs.hermes.jpaperformance.persistence.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 1/17/13
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ConfigData {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_GEN")
    @SequenceGenerator(
            name = "SEQ_GEN",
            sequenceName = "myConfigSeq", allocationSize = 1000)
    private Long id;
    @Column
    private String key;
    @Column
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConfigData");
        sb.append("{id=").append(id);
        sb.append(", key='").append(key).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
