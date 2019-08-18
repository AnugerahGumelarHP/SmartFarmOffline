package com.battistradadeveloper.smartfarmoffline.model;

public class DataModel {
    private int id;
    public String name;
    private String type;
    private String year;
    private String descrip;
    private String owner;
    private String owneradd;

    public DataModel(){
    }

    public DataModel(String name, String type, String year, String descrip, String owner, String owneradd) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.descrip = descrip;
        this.owner = owner;
        this.owneradd = owneradd;
    }

    public int getId(){
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

    public String getType(){return type;}

    public void setType(String type) {this.type = type; }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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

}
