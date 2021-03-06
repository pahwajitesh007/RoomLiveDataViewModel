package com.example.jiteshp.roomlivedataviewmodel.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTmestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
