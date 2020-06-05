package com.example.mytopmovies.data.locale_storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityLocale.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract LocaleDao getDao();
}
