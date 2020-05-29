package com.certant.app.utils;

import org.apache.commons.lang3.StringUtils;

public class WrapperEvolution {
    private String name;
    private Object types;
    private Integer levelNeeded;

    public WrapperEvolution() {
    }

    public WrapperEvolution(String name, Integer levelNeeded) {
        this.name = name;
        this.levelNeeded = levelNeeded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevelNeeded() {
        return levelNeeded;
    }

    public void setLevelNeeded(Integer levelNeeded) {
        this.levelNeeded = levelNeeded;
    }

    public Object getTypes() {
        return types;
    }

    public void setTypes(Object types) {
        this.types = types;
    }
    
    @Override
    public String toString() {   
        String msg = StringUtils.join(
                    "Name: ", name, "; ",
                    "Type/s: ", types.toString(), "; ",
                    "Level: ", levelNeeded, ".\n"
                    );
        return msg;
    }
    
    public boolean isValid() {
        return name != null && types != null && levelNeeded != null;
    }
}
