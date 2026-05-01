package com.example.demo.model;

public class Pet {
    private Long id;
    private String name;
    private String owner;
    private String type;
    private String race;
    private int realAge;

    private int calculateHumanAge() {
        return realAge * 3;
    }

    public Pet(Long id, String name, String owner, String type, String race, int realAge) {
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.race = race;
        this.realAge = realAge;
        this.id=id;
    }

    public Pet(String name, String owner, String type, String race, int realAge) {
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.race = race;
        this.realAge = realAge;
    }

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

    public int getRealAge() {
        return realAge;
    }

    public void setRealAge(int realAge) {
        this.realAge = realAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", type='" + type + '\'' +
                ", race='" + race + '\'' +
                ", realAge=" + realAge +
                ", humanAge=" + String.valueOf(calculateHumanAge())+
                '}';
    }
}
