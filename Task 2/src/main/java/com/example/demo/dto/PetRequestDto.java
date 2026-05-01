package com.example.demo.dto;

public class PetRequestDto {

    private String name;
    private String owner;
    private String type;
    private String race;
    private Integer realAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getRealAge() {
        return realAge;
    }

    public void setRealAge(Integer realAge) {
        this.realAge = realAge;
    }
}
