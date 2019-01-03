package com.smashstats.fighters;

import javax.persistence.*;

@Entity
@Table(name = "Fighters")
public class Fighter {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Fighter() {}
    public Fighter(String name) {
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

        if(object instanceof Fighter) {
            Fighter other = (Fighter)object;
            return this.name.equalsIgnoreCase(other.getName());
        }
        else return false;
    }
}
