package com.example.marvel.data.service

import com.example.marvel.data.constant.baseUrl
import com.example.marvel.data.model.CharacterDataWrapper
import com.example.marvel.data.model.ComicDataWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Api {
    @GET(value = "characters")
    suspend fun getCharacters(
        @Query("ts") ts:String,
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("hash") hash : String
    ): CharacterDataWrapper


    @GET(value = "characters/{character_id}/comics")
    suspend fun getComics(
        @Path("character_id") character_id: Int,
        @Query("ts") ts:String,
        @Query("apikey") apikey: String,
        @Query("startYear") startYear: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String,
        @Query("hash") hash : String
        ): ComicDataWrapper


    companion object{
        operator fun invoke(): Api {
            val client = OkHttpClient.Builder().readTimeout(1200, TimeUnit.SECONDS).connectTimeout(1200,TimeUnit.SECONDS).build()
            val retrofit = Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
            return retrofit.create(Api::class.java)
        }
    }
}