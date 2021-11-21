package com.example.marvel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CharacterModel(
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("image") val imagePath : String
)
