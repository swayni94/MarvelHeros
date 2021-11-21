package com.example.marvel.data.db

import androidx.room.*
import com.example.marvel.data.model.Character
import com.example.marvel.data.model.CharacterModel

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(character : CharacterModel)

    @Query("SELECT * FROM CharacterModel")
    suspend fun getPopularCharacters(): List<CharacterModel>

    @Query("SELECT * FROM CharacterModel WHERE CharacterModel.id == :id")
    suspend fun getCharacter(id: Int): CharacterModel

    @Delete
    suspend fun delete(character: CharacterModel)
}