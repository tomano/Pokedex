package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ItemPocket.TABLE_NAME)
data class ItemPocket(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = ITEM_CATEGORY_NAME_LIST)
    val itemCategoryNameList: List<String>

) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "ItemPocket"
        const val ID = "id"
        const val NAME = "name"
        const val ITEM_CATEGORY_NAME_LIST = "itemCategoryNameList"
    }
}