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
import javax.persistence.Table;

@Entity
@Table(name = "abilities")
/*
Implemento Comparable, para luego poder ordenar los Set's, una
vez convertidos en List's, por Id
*/
public class Ability implements Comparable<Ability> {
    @Id
    @GeneratedValue
    @Column(name = "aty_id")
    private Long id;
    @Column(name = "aty_name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Pokemon> pokemons = new HashSet<Pokemon>();

    public Ability() {
    }

    public Ability(String name, Set<Pokemon> pokemons) {
        this.name = name;
        this.pokemons = pokemons;
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

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Ability other = (Ability) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ability ay) {
        return this.getId().compareTo(ay.getId());
    }  
}