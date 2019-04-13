package com.minageorge.chatdemo.store

import androidx.room.TypeConverter
import java.util.*

object DateTypeConverters {

    @TypeConverter
    @JvmStatic
    fun toDate(value: Long): Date? {
        return Date(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date): Long {
        return date.time
    }
}