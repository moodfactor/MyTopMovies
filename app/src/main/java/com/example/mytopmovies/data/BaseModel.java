package com.example.mytopmovies.data;

public  class BaseModel {
    private String country;
    private String name;

    public BaseModel(String name, String country) {
        this.country = country;
        this.name = name;
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
}
