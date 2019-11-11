package com.benjaminledet.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benjaminledet.pokedex.data.local.converter.StringListConverter
import com.benjaminledet.pokedex.data.local.dao.ItemCategoryDao
import com.benjaminledet.pokedex.data.local.dao.ItemDao
import com.benjaminledet.pokedex.data.local.dao.ItemPocketDao
import com.benjaminledet.pokedex.data.local.dao.PokemonDao
import com.benjaminledet.pokedex.data.model.Item
import com.benjaminledet.pokedex.data.model.ItemCategory
import com.benjaminledet.pokedex.data.model.ItemPocket
import com.benjaminledet.pokedex.data.model.Pokemon

@Database(
    entities = [
        Pokemon::class, Item::class, ItemPocket::class, ItemCategory::class
    ],
    version = PokedexDatabase.VERSION, exportSchema = true
)
@TypeConverters(StringListConverter::class)
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    abstract fun itemCategoryDao(): ItemCategoryDao

    abstract fun itemPocketDao(): ItemPocketDao

    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "PokedexDatabase"
        const val VERSION = 1
    }
}