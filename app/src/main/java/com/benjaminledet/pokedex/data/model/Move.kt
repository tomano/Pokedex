package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Move.TABLE_NAME)
data class Move(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String
) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "Move"
        const val ID = "id"
        const val NAME = "name"
    }
}