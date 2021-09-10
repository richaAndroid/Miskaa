package com.example.miskaa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.miskaa.util.Constants;
import com.google.gson.annotations.SerializedName;

@Entity (tableName = Constants.TABLE_LANGUAGE)
public class Language {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @SerializedName("iso639_1")
    public String iso6391;
    @SerializedName("iso639_2")
    public String iso6392;
    @SerializedName("name")
    public String name;
    @SerializedName("nativeName")
    public String nativeName;

    public Language(int id, String iso6391, String iso6392, String name, String nativeName) {
        this.id = id;
        this.iso6391 = iso6391;
        this.iso6392 = iso6392;
        this.name = name;
        this.nativeName = nativeName;
    }

    @Ignore
    public Language(String iso6391, String iso6392, String name, String nativeName) {
        this.id = id;
        this.iso6391 = iso6391;
        this.iso6392 = iso6392;
        this.name = name;
        this.nativeName = nativeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
