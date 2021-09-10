package com.example.miskaa.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.miskaa.database.dao.CountryDao;
import com.example.miskaa.model.BorderConverter;
import com.example.miskaa.model.CountryPojo;
import com.example.miskaa.model.Language;
import com.example.miskaa.model.LanguageConverter;
import com.example.miskaa.util.Constants;

@Database(entities = { CountryPojo.class, Language.class}, version = 1, exportSchema = false)
@TypeConverters({LanguageConverter.class, BorderConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        synchronized (context){
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                        .allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }

    public void cleanUp(){
        INSTANCE = null;
    }

}
