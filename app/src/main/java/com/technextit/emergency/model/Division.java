package com.technextit.emergency.model;

/**
 * Created by sohel.technext on 7/4/2015.
 */
public class Division {

    private Long id;
    private String name;

    public Division(Long id, String name){
        this.id = id;
        this.name = name;
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
}
