package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("urls") val urls : List<Url>,
    @SerializedName("thumbnail") val thumbnail:Image ,
    @SerializedName("comics") val comics : ComicList,
    @SerializedName("stories") val stories : StoryList,
    @SerializedName("events") val events : EventList,
    @SerializedName("series") val series : SeriesList
)
