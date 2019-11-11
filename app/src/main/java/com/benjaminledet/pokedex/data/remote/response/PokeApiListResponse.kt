package com.benjaminledet.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokeApiListResponse(

    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String?,

    @SerializedName("previous")
    val previous: String?,

    @SerializedName("results")
    val results: List<ApiResourceResponse>
)