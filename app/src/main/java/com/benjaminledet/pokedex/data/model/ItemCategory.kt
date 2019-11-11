package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = ItemCategory.TABLE_NAME,
    foreignKeys = [
        ForeignKey(entity = ItemPocket::class, parentColumns = arrayOf(ItemPocket.ID), childColumns = arrayOf(ItemCategory.ITEM_POCKET_ID), onDelete = ForeignKey.CASCADE)
    ]
)
data class ItemCategory(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = ITEM_POCKET_ID)
    val itemPocketId: Int,

    @ColumnInfo(name = ITEM_NAME_LIST)
    val itemNameList: List<String>

) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "ItemCategory"
        const val ID = "id"
        const val NAME = "name"
        const val ITEM_POCKET_ID = "itemPocketId"
        const val ITEM_NAME_LIST = "itemNameList"
    }
}