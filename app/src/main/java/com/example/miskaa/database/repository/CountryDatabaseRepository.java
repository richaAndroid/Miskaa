package com.example.miskaa.database.repository;

import com.example.miskaa.database.dao.CountryDao;
import com.example.miskaa.model.CountryPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountryDatabaseRepository {
    private CountryDao countryDao;
    private static final int NUMBER_OF_THREADS=2;
    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public CountryDatabaseRepository(CountryDao dao){
        countryDao = dao;
    }

    public List<CountryPojo> getAllCountries(){
        return countryDao.getAllCountries();
    }

    public void deleteAllCountries(){
        databaseWriteExecutor.execute(()->{ countryDao.deleteAll(); });
    }

    public List<Long> insertCountryList(List<CountryPojo> list){
        List<Long> idList  = new ArrayList();
        databaseWriteExecutor.execute(()->{ countryDao.insertCountryList(list); });
        return idList;
    }
}
