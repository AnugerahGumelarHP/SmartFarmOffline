package com.battistradadeveloper.smartfarmoffline.model;

public class DataModel {
    private int id;
    public String name, type, year, description, owner, owneradd;
    private double price;

    public DataModel(int id, String name, String type, String year, String description, String owner,
                     String owneradd, double price) {
        this.id= id;
        this.name = name;
        this.type = type;
        this.year = year;
        this.description = description;
        this.owner = owner;
        this.owneradd = owneradd;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwneradd() {
        return owneradd;
    }

    public void setOwneradd(String owneradd) {
        this.owneradd = owneradd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
