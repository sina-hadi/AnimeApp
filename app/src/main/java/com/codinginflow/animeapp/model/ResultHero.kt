package com.codinginflow.animeapp.model

import com.codinginflow.animeapp.Hero
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResultHero(
    @SerializedName("heroes")
    val heroes: List<Hero>,
    @SerializedName("message")
    val message: String,
    @SerializedName("nextPage")
    val nextPage: Int,
    @SerializedName("prevPage")
    val prevPage: Int,
    @SerializedName("success")
    val success: Boolean
)