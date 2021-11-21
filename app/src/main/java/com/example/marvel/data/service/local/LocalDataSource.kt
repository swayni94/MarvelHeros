package com.example.marvel.data.service.local

import com.example.marvel.data.db.AppDatabase
import com.example.marvel.data.model.Character
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.until.ResultData

interface LocalDataSource {
    suspend fun insertCharacter(character: CharacterModel): ResultData<Boolean>
    suspend fun getCharacter(id:Int): ResultData<CharacterModel>
    suspend fun getPopularCharacterList() : ResultData<List<CharacterModel>>
    suspend fun deleteCharacter(character: CharacterModel): ResultData<Boolean>
}