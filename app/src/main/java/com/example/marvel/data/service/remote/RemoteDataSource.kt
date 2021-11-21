package com.example.marvel.data.service.remote

import com.example.marvel.data.model.CharacterDataWrapper
import com.example.marvel.data.model.ComicDataWrapper
import com.example.marvel.data.until.ResultData

interface RemoteDataSource {
    suspend fun getCharacters(offset : Int, limit : Int): ResultData<CharacterDataWrapper>
    suspend fun getComicsByCharacterId(character_id : Int, startYear : Int, limit:Int, orderBy:String):ResultData<ComicDataWrapper>
}