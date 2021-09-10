package com.example.miskaa.network;

import com.example.miskaa.model.CountryPojo;

import java.util.List;

import retrofit2.Call;

public class CountryRetrofitRepository {

    private RetrofitService retrofitServiceInstance;
    private static CountryRetrofitRepository retrofitRepository;

    public CountryRetrofitRepository(RetrofitService retrofitServiceInstance) {
        this.retrofitServiceInstance = retrofitServiceInstance;
    }

    public static CountryRetrofitRepository getInstance(RetrofitService retrofitServiceInstance){
        if (retrofitRepository == null){
            retrofitRepository = new CountryRetrofitRepository(retrofitServiceInstance);
        }
        return retrofitRepository;
    }

    public Call<List<CountryPojo>> getCountriesInAsia(){
        return retrofitServiceInstance.getCountriesInAsia();
    }
}
