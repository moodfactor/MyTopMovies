package com.example.mytopmovies.data.locale_storage;

import androidx.room.Entity;

@Entity(tableName = "entityLocale",primaryKeys = {"keyId"})
public class EntityLocale {
    private long keyId;

    public EntityLocale(long keyId) {
        this.keyId = keyId;
    }

    public long getKeyId() {
        return keyId;
    }
}