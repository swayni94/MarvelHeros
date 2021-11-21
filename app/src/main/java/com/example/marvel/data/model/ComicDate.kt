package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ComicDate(
    @SerializedName("type") val type : String,
    @SerializedName("date") val date : String
)
