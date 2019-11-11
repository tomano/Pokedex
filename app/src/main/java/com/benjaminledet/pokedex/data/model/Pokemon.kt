package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Pokemon.TABLE_NAME)
data class Pokemon(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = ICON_URL)
    val iconUrl: String?,

    @Embedded
    val detail: PokemonDetail?
) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "Pokemon"
        const val ID = "id"
        const val NAME = "name"
        const val ICON_URL = "iconUrl"
    }
}