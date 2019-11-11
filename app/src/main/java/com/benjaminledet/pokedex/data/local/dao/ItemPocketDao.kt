package com.benjaminledet.pokedex.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benjaminledet.pokedex.data.model.ItemPocket

@Dao
abstract class ItemPocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ItemPocket>): List<Long>

    @Query("SELECT * FROM ${ItemPocket.TABLE_NAME} WHERE ${ItemPocket.ID} = :id")
    abstract suspend fun getById(id: Int?): ItemPocket?

    @Query("SELECT * FROM ${ItemPocket.TABLE_NAME}")
    abstract fun getAllPaged(): DataSource.Factory<Int, ItemPocket>
}