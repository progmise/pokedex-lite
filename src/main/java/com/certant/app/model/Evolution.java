package com.certant.app.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evolutions")
/*
Implemento Comparable, para luego poder ordenar los Set's, una
vez convertidos en List's, por Id
*/
public class Evolution implements Comparable<Evolution> {
    @Id
    @GeneratedValue
    @Column(name = "ev_id")
    private Long id;
    @Column(name = "ev_name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "evolutions")
    private Set<Type> types = new HashSet<Type>();
    @Column(name = "ev_level_needed")
    private Integer levelNeeded;
    @ManyToOne
    private Pokemon pokemon;

    public Evolution() {
    }

    public Evolution(String name, Set<Type> types, Integer levelNeeded, Pokemon pokemon) {
        this.name = name;
        this.types = types;
        this.levelNeeded = levelNeeded;
        this.pokemon = pokemon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Integer getLevelNeeded() {
        return levelNeeded;
    }

    public void setLevelNeeded(Integer levelNeeded) {
        this.levelNeeded = levelNeeded;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.levelNeeded);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evolution other = (Evolution) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.levelNeeded, other.levelNeeded)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Evolution ev) {
        return this.getId().compareTo(ev.getId());
    }
}