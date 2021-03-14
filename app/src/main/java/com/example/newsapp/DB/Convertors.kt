package com.androiddevs.mvvmnewsapp.DB

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.Models.Source

class Convertors {


    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}
