package com.example.miskaa.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.miskaa.database.AppDatabase;
import com.example.miskaa.database.repository.CountryDatabaseRepository;
import com.example.miskaa.model.CountryPojo;
import com.example.miskaa.network.CountryRetrofitRepository;
import com.example.miskaa.network.RetrofitSetup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private CountryDatabaseRepository countryDatabaseRepository;
    private CountryRetrofitRepository countryRetrofitRepository;
    private AppDatabase dbInstance;
    public MutableLiveData<List<CountryPojo>> countryListStoredInDb = new MutableLiveData();
    public MutableLiveData<List<CountryPojo>> countryList = new MutableLiveData();
    public MutableLiveData<String> errorMsg = new MutableLiveData();

    public MainViewModel(@NonNull Application application) {
        super(application);
        dbInstance = AppDatabase.getInstance(application);
        countryDatabaseRepository = new CountryDatabaseRepository(dbInstance.countryDao());
        countryRetrofitRepository = new CountryRetrofitRepository(RetrofitSetup.createRetrofitServiceInstance());
    }

    public void getAllCountriesFromNetwork(){
        countryRetrofitRepository.getCountriesInAsia().enqueue(new Callback<List<CountryPojo>>() {
            @Override
            public void onResponse(Call<List<CountryPojo>> call, Response<List<CountryPojo>> response) {
                countryList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CountryPojo>> call, Throwable t) {
                errorMsg.setValue(t.getMessage());
            }
        });
    }

    public void getAllCountriesFromDatabase(){
        if(countryListStoredInDb == null){
            countryList.setValue(countryDatabaseRepository.getAllCountries());
        }else {
            countryList.setValue(countryListStoredInDb.getValue());
        }
    }

    public void deleteAllCountriesFromDatabase(){
        dbInstance.clearAllTables();
        errorMsg.setValue("All stored data deleted");
    }

    public void insertCountriesInDB(List<CountryPojo> list){
        countryDatabaseRepository.insertCountryList(list);
    }
    public void clearDB() {
        dbInstance.cleanUp();
        countryRetrofitRepository = null;
        countryDatabaseRepository = null;
    }

    public void checkForStoredCountryInDatabase() {
        List<CountryPojo> list = countryDatabaseRepository.getAllCountries();
        countryListStoredInDb.setValue(list);
    }
}
