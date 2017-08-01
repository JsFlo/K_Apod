package com.example.jsflo.kapod.data.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class ApodDbConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        if (value == null) {
            return null;
        } else {
            return new Date(value);
        }
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date.getTime();
    }
}
