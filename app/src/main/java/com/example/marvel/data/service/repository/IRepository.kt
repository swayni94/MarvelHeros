package com.example.marvel.data.service.repository

import com.example.marvel.data.model.CharacterDataWrapper
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.model.ComicDataWrapper
import com.example.marvel.data.service.local.LocalDataSource
import com.example.marvel.data.until.ResultData

interface IRepository {
    suspend fun getCharacters(offset : Int, limit : Int): ResultData<CharacterDataWrapper>
    suspend fun getComicsByCharacterId(character_id : Int, startYear : Int, limit:Int, orderBy:String): ResultData<ComicDataWrapper>


    suspend fun insertCharacter(character: CharacterModel, localDataSource: LocalDataSource): ResultData<Boolean>
    suspend fun getCharacter(id:Int, localDataSource: LocalDataSource): ResultData<CharacterModel>
    suspend fun getPopularCharacterList(localDataSource: LocalDataSource) : ResultData<List<CharacterModel>>
    suspend fun deleteCharacter(character: CharacterModel, localDataSource: LocalDataSource): ResultData<Boolean>
}