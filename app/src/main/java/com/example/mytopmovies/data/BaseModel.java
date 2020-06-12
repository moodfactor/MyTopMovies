package com.example.mytopmovies.data;

public  class BaseModel {
    private String country;
    private String name;
    private int id;

    public BaseModel(String name, String country,int id) {
        this.country = country;
        this.name = name;
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
