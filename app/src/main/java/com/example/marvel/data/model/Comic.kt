package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comic(
    @SerializedName("id") val id : Int,
    @SerializedName("digitalId") val digitalId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("issueNumber") val issueNumber : Double,
    @SerializedName("variantDescription") val variantDescription : String,
    @SerializedName("description") val description : String,
    @SerializedName("modified") val modified : String,
    @SerializedName("isbn") val isbn : String,
    @SerializedName("upc") val upc : String,
    @SerializedName("diamondCode") val diamondCode : String,
    @SerializedName("ean") val ean : String,
    @SerializedName("issn") val iSsn : String,
    @SerializedName("format") val format : String,
    @SerializedName("pageCount") val pageCount : Int,
    @SerializedName("textObjects") val textObjects : List<TextObject>,
    @SerializedName("resourceURI") val resourceURI : String,
    @SerializedName("urls") val urls : List<Url>,
    @SerializedName("series") val series : SeriesSummary,
    @SerializedName("variants") val variants : List<ComicSummary>,
    @SerializedName("collections") val collections : List<ComicSummary>,
    @SerializedName("collectedIssues") val collectedIssues : List<ComicSummary>,
    @SerializedName("dates") val dates : List<ComicDate>,
    @SerializedName("prices") val prices : List<ComicPrice>,
    @SerializedName("thumbnail") val thumbnail : Image,
    @SerializedName("images") val images : List<Image>,
    @SerializedName("creators") val creators : CreatorList,
    @SerializedName("characters") val characters : CharacterList,
    @SerializedName("stories") val stories : StoryList,
    @SerializedName("events") val events : EventList
)
