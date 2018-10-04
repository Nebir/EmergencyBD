package com.technextit.emergency.model;

public class Service {
    private Long id;
    private String name;
    private String description;
    private String image_url;

    public Service(Long id, String name,String description ,String image_url){
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
