package com.codinginflow.animeapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Hero(
    @SerializedName("abilities")
    val abilities: List<String>,
    @SerializedName("about")
    val about: String,
    @SerializedName("day")
    val day: String,
    @SerializedName("family")
    val family: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("month")
    val month: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("natureTypes")
    val natureTypes: List<String>,
    @SerializedName("power")
    val power: Int,
    @SerializedName("rating")
    val rating: Double
) : Parcelable