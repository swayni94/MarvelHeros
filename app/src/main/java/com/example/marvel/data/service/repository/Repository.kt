package com.example.marvel.data.service.repository

import com.example.marvel.data.model.Character
import com.example.marvel.data.model.CharacterDataWrapper
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.model.ComicDataWrapper
import com.example.marvel.data.service.local.LocalDataSource
import com.example.marvel.data.service.remote.RemoteDataSource
import com.example.marvel.data.until.ResultData
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource : RemoteDataSource

): IRepository {

    override suspend fun getCharacters(offset: Int, limit: Int): ResultData<CharacterDataWrapper> {
        return when(val result = remoteDataSource.getCharacters(offset, limit)){
            is ResultData.Success ->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun getComicsByCharacterId(
        character_id: Int,
        startYear: Int,
        limit: Int,
        orderBy: String
    ): ResultData<ComicDataWrapper> {
        return when(val result = remoteDataSource.getComicsByCharacterId(character_id, startYear, limit, orderBy)){
            is ResultData.Success->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun insertCharacter(
        character: CharacterModel,
        localDataSource: LocalDataSource
    ): ResultData<Boolean> {
        return when(val result = localDataSource.insertCharacter(character)){
            is ResultData.Success->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun getCharacter(id: Int, localDataSource: LocalDataSource): ResultData<CharacterModel> {
        return when(val result = localDataSource.getCharacter(id)){
            is ResultData.Success->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun getPopularCharacterList(localDataSource: LocalDataSource): ResultData<List<CharacterModel>> {
        return when(val result = localDataSource.getPopularCharacterList()){
            is ResultData.Success->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun deleteCharacter(
        character: CharacterModel, localDataSource: LocalDataSource
    ): ResultData<Boolean> {
        return when(val result = localDataSource.deleteCharacter(character)){
            is ResultData.Success->{
                ResultData.Success(result.data)
            }
            is ResultData.Error ->{
                ResultData.Error(result.exception)
            }
        }
    }

}