package com.example.miskaa.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.example.miskaa.util.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = Constants.TABLE_COUNTRY)
public class CountryPojo {
    @PrimaryKey
    @NonNull
    @SerializedName("name")
    public String name = "";
    @SerializedName("capital")
    public String capital;
    @SerializedName("region")
    public String region;
    @SerializedName("subregion")
    public String subregion;
    @SerializedName("population")
    public Integer population;
    @SerializedName("borders")
    public ArrayList<String> borders ;
    @SerializedName("languages")
    public ArrayList<Language> languages ;
    @SerializedName("flag")
    public String flag;

    public CountryPojo(String name, String capital, String region, String subregion, Integer population, ArrayList<String> borders, ArrayList<Language> languages, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @BindingAdapter("flagImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}