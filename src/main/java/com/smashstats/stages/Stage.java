package com.smashstats.stages;

import javax.persistence.*;

@Entity
@Table(name = "Stages")
public class Stage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Stage() {}
    public Stage(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }

        if(object instanceof Stage) {
            Stage other = (Stage)object;
            return this.name.equalsIgnoreCase(other.getName());
        }
        else return false;
    }
}
