package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CharacterDataContainer(
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val results : List<Character>
)