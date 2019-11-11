package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = Item.TABLE_NAME,
    foreignKeys = [
        ForeignKey(entity = ItemCategory::class, parentColumns = arrayOf(ItemCategory.ID), childColumns = arrayOf(Item.ITEM_CATEGORY_ID), onDelete = ForeignKey.CASCADE)
    ]
)
data class Item(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = ICON_URL)
    val iconUrl: String?,

    @ColumnInfo(name = ITEM_CATEGORY_ID)
    val itemCategoryId: Int

) {

    override fun toString(): String = name

    companion object {

        const val TABLE_NAME = "Item"
        const val ID = "id"
        const val NAME = "name"
        const val ICON_URL = "iconUrl"
        const val ITEM_CATEGORY_ID = "itemCategoryId"
    }
}