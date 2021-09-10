package com.example.miskaa.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.miskaa.model.CountryPojo;
import com.example.miskaa.util.Constants;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert (onConflict =  OnConflictStrategy.REPLACE)
    public List<Long> insertCountryList(List<CountryPojo> list);

    @Query("DELETE FROM "+Constants.TABLE_COUNTRY )
    public void deleteAll();

    @Query("SELECT * FROM "+ Constants.TABLE_COUNTRY)
    List<CountryPojo> getAllCountries();
}
