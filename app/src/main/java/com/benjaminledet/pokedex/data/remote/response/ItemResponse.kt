package com.benjaminledet.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprites: Map<String, String>
) {

    companion object {

        const val DEFAULT_SPRITE = "default"
    }
}