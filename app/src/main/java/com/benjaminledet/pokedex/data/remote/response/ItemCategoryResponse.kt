package com.benjaminledet.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class ItemCategoryResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("items")
    val items: List<ApiResourceResponse>
)