package com.benjaminledet.pokedex.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benjaminledet.pokedex.data.model.Item

@Dao
abstract class ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<Item>): List<Long>

    @Query("SELECT * FROM ${Item.TABLE_NAME} WHERE ${Item.ID} = :id")
    abstract suspend fun getById(id: Int?): Item?

    @Query("SELECT * FROM ${Item.TABLE_NAME} WHERE ${Item.ITEM_CATEGORY_ID} = :itemCategoryId")
    abstract fun getAllPagedOfCategory(itemCategoryId: Int?): DataSource.Factory<Int, Item>
}