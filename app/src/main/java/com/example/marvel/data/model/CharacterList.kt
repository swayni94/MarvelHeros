package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("available") val available : Int,
    @SerializedName("returned") val returned : Int,
    @SerializedName("collectionURI") val collectionURI : String,
    @SerializedName("items") val items : List<CharacterSummary>
)
