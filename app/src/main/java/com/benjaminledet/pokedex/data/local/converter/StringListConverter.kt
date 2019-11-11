package com.benjaminledet.pokedex.data.local.converter

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromList(data: List<String>?): String = data?.joinToString(separator = ",") ?: ""

    @TypeConverter
    fun toList(data: String?): List<String> = data?.split(",") ?: listOf()

}