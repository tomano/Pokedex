package com.benjaminledet.pokedex.data.model

import androidx.room.ColumnInfo

data class PokemonDetail(

    @ColumnInfo(name = WEIGHT)
    val weight: Double,

    @ColumnInfo(name = HEIGHT)
    val height: Double,

    @ColumnInfo(name = TYPES)
    val types: List<String>
) {

    companion object {
        const val WEIGHT = "weight"
        const val HEIGHT = "height"
        const val TYPES = "types"
    }
}