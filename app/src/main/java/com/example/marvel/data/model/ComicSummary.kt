package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ComicSummary(
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("name") val name : String
)
