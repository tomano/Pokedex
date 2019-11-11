package com.benjaminledet.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class ItemPocketResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("categories")
    val categories: List<ApiResourceResponse>
)