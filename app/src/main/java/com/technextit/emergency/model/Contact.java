package com.technextit.emergency.model;

/**
 * Created by sohel.technext on 7/4/2015.
 */
public class Contact {

    private Long id;
    private String name;
    private String phone_number;
    private Long service_id;
    private Long division_id;
    private double lat;
    private  double lon;

    public Contact(Long id, String name, String phone_number, Long service_id, Long division_id){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.service_id = service_id;
        this.division_id = division_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }

    public Long getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Long division_id) {
        this.division_id = division_id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
