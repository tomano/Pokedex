package com.benjaminledet.pokedex.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benjaminledet.pokedex.data.model.ItemCategory

@Dao
abstract class ItemCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ItemCategory>): List<Long>

    @Query("SELECT * FROM ${ItemCategory.TABLE_NAME} WHERE ${ItemCategory.ID} = :id")
    abstract suspend fun getById(id: Int?): ItemCategory?

    @Query("SELECT * FROM ${ItemCategory.TABLE_NAME} WHERE ${ItemCategory.ITEM_POCKET_ID} = :itemPocketId")
    abstract fun getAllPagedOfPocket(itemPocketId: Int?): DataSource.Factory<Int, ItemCategory>
}