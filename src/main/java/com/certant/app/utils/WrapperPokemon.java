package com.certant.app.utils;

import org.apache.commons.lang3.StringUtils;

public class WrapperPokemon {
    private Long id;
    private String name;
    private Object types;
    private Object abilities;
    private Object evolutions;
    private Integer levelIsFound;
    private SearchOption searchOption;

    public WrapperPokemon() {
    }

    public WrapperPokemon(Long id, String name, Integer levelIsFound, SearchOption searchOption) {
        this.id = id;
        this.name = name;
        this.levelIsFound = levelIsFound;
        this.searchOption = searchOption;
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

    public Integer getLevelIsFound() {
        return levelIsFound;
    }

    public void setLevelIsFound(Integer levelIsFound) {
        this.levelIsFound = levelIsFound;
    }

    public Object getTypes() {
        return types;
    }

    public void setTypes(Object types) {
        this.types = types;
    }

    public Object getAbilities() {
        return abilities;
    }

    public void setAbilities(Object abilities) {
        this.abilities = abilities;
    }

    public Object getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(Object evolutions) {
        this.evolutions = evolutions;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    public void setOption(SearchOption searchOption) {
        this.searchOption = searchOption;
    }

    @Override
    public String toString() {   
        String msg = StringUtils.join(
                    "Name: ", name, "; ",
                    "Type/s: ", types.toString(), "; ",
                    "Level: ", levelIsFound, ".\n"
                    );
        return msg;
    }
}