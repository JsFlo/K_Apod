package com.example.jsflo.kapod.data.database;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.LocalDate;

public class ApodDbConverter {
    @TypeConverter
    public static LocalDate fromTimeStamp(Long value) {
        return new LocalDate(value);
    }

    @TypeConverter
    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate.toDateTimeAtStartOfDay().getMillis();
    }
}
