package com.example.miskaa.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BorderConverter {

    @TypeConverter
    public String fromList(ArrayList<String> borderList) {
        if (borderList == null) {
            return (null);
        }
        return new Gson().toJson(borderList);
    }

    @TypeConverter
    public ArrayList<String> toCountryLangList(String str) {
        if (str == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(str, type);
    }
}