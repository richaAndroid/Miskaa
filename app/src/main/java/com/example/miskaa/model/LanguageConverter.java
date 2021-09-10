package com.example.miskaa.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LanguageConverter {

    @TypeConverter
    public String fromCountryLangList(ArrayList<Language> languageList) {
        if (languageList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Language>>() {}.getType();
        String json = gson.toJson(languageList, type);
        return json;
    }

    @TypeConverter
    public ArrayList<Language> toCountryLangList(String langStr) {
        if (langStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Language>>() {}.getType();
        ArrayList<Language> languageList = gson.fromJson(langStr, type);
        return languageList;
    }
}