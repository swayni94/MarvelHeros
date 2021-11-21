package com.example.marvel.data.service.remote

import com.example.marvel.data.constant.apikey
import com.example.marvel.data.constant.md5
import com.example.marvel.data.constant.privateApiKey
import com.example.marvel.data.model.CharacterDataWrapper
import com.example.marvel.data.model.ComicDataWrapper
import com.example.marvel.data.service.Api
import com.example.marvel.data.until.ResultData

class RemoteDataSourceImpl : RemoteDataSource {

    private val service = Api.invoke()

    override suspend fun getCharacters(offset: Int, limit: Int): ResultData<CharacterDataWrapper> {
        return try {
            val hash = "${1}${privateApiKey}${apikey}"
            val request = service.getCharacters("1", apikey, offset, limit, hash.md5())
            ResultData.Success(request)
        }catch (e : Exception){
            ResultData.Error(e)
        }
    }

    override suspend fun getComicsByCharacterId(
        character_id: Int,
        startYear: Int,
        limit: Int,
        orderBy: String
    ): ResultData<ComicDataWrapper> {
        return try {
            val hash = "${1}${privateApiKey}${apikey}"
            val request = service.getComics(character_id, "1", apikey, startYear, limit, orderBy, hash.md5())
            ResultData.Success(request)
        }catch (e : Exception){
            ResultData.Error(e)
        }
    }
}