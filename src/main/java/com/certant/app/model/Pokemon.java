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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pokemons")
/*
Implemento Comparable, para luego poder ordenar los Set's, una
vez convertidos en List's, por Id
*/
public class Pokemon implements Comparable<Pokemon>{
    @Id
    @GeneratedValue
    @Column(name = "pk_id")
    private Long id;
    @Column(name = "pk_name")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "pokemons") 
    private Set<Type> types = new HashSet<Type>();
    @Column(name = "pk_level_is_found")
    private Integer levelIsFound;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "pokemons")
    private Set<Ability> abilities = new HashSet<Ability>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pokemon")
    private Set<Evolution> evolutions = new HashSet<Evolution>();    

    public Pokemon() {
    }

    public Pokemon(String name, Set<Type> types, Integer levelIsFound, Set<Ability> abilities, Set<Evolution> evolutions) {
        this.name = name;
        this.types = types;
        this.levelIsFound = levelIsFound;
        this.abilities = abilities;
        this.evolutions = evolutions;
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

    public Integer getLevelIsFound() {
        return levelIsFound;
    }

    public void setLevelIsFound(Integer levelIsFound) {
        this.levelIsFound = levelIsFound;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public Set<Evolution> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(Set<Evolution> evolutions) {
        this.evolutions = evolutions;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "name=" + name + ", levelIsFound=" + levelIsFound + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.levelIsFound);
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
        final Pokemon other = (Pokemon) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.levelIsFound, other.levelIsFound)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Pokemon pk) {
        return this.getId().compareTo(pk.getId());
    }
}