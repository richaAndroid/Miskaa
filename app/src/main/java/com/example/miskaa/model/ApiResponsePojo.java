package com.example.miskaa.model;

import java.util.ArrayList;

public class ApiResponsePojo {
    private ArrayList<CountryPojo> countryList;

    public ApiResponsePojo(ArrayList<CountryPojo> countryList) {
        this.countryList = countryList;
    }

    public ArrayList<CountryPojo> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<CountryPojo> countryList) {
        this.countryList = countryList;
    }
}
