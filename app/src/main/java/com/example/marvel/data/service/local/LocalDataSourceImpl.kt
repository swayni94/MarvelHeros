package com.example.marvel.data.service.local

import com.example.marvel.data.db.AppDatabase
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.until.ResultData

class LocalDataSourceImpl(private val appDatabase: AppDatabase) : LocalDataSource {


    override suspend fun insertCharacter(character: CharacterModel):ResultData<Boolean> {
        return try {
            appDatabase.characterDao().add(character)
            ResultData.Success(true)
        }catch (e : Exception){
            ResultData.Error(e)
        }
    }

    override suspend fun getCharacter(id: Int): ResultData<CharacterModel> {
        return try {
            val result = appDatabase.characterDao().getCharacter(id)
            ResultData.Success(result)
        }catch (e: Exception){
            ResultData.Error(e)
        }
    }

    override suspend fun getPopularCharacterList(): ResultData<List<CharacterModel>> {
        return try {
            val result = appDatabase.characterDao().getPopularCharacters()
            ResultData.Success(result)
        }catch (e : java.lang.Exception){
            ResultData.Error(e)
        }
    }

    override suspend fun deleteCharacter(character: CharacterModel):ResultData<Boolean> {
        return try {
            appDatabase.characterDao().delete(character)
            ResultData.Success(true)
        }catch (e :Exception){
            ResultData.Error(e)
        }
    }


}