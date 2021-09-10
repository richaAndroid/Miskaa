package com.example.miskaa.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetup {

    private static RetrofitService retrofitService = null;

    public synchronized static RetrofitService createRetrofitServiceInstance() {
        if (retrofitService == null) {
            retrofitService = getRetrofitInstance().create(RetrofitService.class);
        }
        return retrofitService;
    }

    private static Retrofit getRetrofitInstance() {
        String BASE_URL = "https://restcountries.eu/";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client =  new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return builder;
    }
}
