package com.example.mytopmovies.data.locale_storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

@Dao
public interface LocaleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListEntry(List<EntityLocale> list);
}
