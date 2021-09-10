package com.example.miskaa.network;

import com.example.miskaa.model.CountryPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    String BASE_URL = "https://restcountries.eu/";

    @GET("rest/v2/region/asia")
    Call<List<CountryPojo>> getCountriesInAsia();
}