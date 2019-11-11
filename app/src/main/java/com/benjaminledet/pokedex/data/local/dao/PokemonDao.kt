package com.benjaminledet.pokedex.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.benjaminledet.pokedex.data.model.Pokemon

@Dao
abstract class PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<Pokemon>): List<Long>

    @Delete
    abstract suspend fun delete(data: List<Pokemon>)

    @Query("DELETE FROM ${Pokemon.TABLE_NAME}")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM ${Pokemon.TABLE_NAME} WHERE ${Pokemon.ID} = :id")
    abstract suspend fun getById(id: Int?): Pokemon?

    @Query("SELECT * FROM ${Pokemon.TABLE_NAME} WHERE ${Pokemon.ID} = :id")
    abstract fun getByIdObservable(id: Int?): LiveData<Pokemon?>

    @Query("SELECT * FROM ${Pokemon.TABLE_NAME}")
    abstract suspend fun getAll(): List<Pokemon>

    @Query("SELECT * FROM ${Pokemon.TABLE_NAME}")
    abstract fun getAllObservable(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM ${Pokemon.TABLE_NAME}")
    abstract fun getAllPaged(): DataSource.Factory<Int, Pokemon>
}