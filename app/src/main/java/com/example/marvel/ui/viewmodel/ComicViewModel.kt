package com.example.marvel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvel.data.model.Character
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.model.ComicDataContainer
import com.example.marvel.data.service.local.LocalDataSource
import com.example.marvel.data.service.repository.Repository
import com.example.marvel.data.until.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComicViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _comicMutableLiveData = MutableLiveData<ComicDataContainer>()
    val comicLiveData get() = _comicMutableLiveData

    private val _errorString = MutableLiveData<String>()
    val errorString : LiveData<String> get() = _errorString

    private var _favoriteMutableLiveData = MutableLiveData<Boolean>()
    val favoriteLiveData get() = _favoriteMutableLiveData

    fun getComics(characterId: Int, startYear: Int, limit:Int, orderBy:String){
        viewModelScope.launch{
            when(val response = repository.getComicsByCharacterId(characterId, startYear, limit, orderBy)){
                is ResultData.Success ->{
                    _comicMutableLiveData.postValue(response.data.data)
                }
                is ResultData.Error ->{
                    _errorString.postValue(response.exception.message)
                }
            }
        }
    }


    fun addFavorite(character: CharacterModel, localDataSource: LocalDataSource){
        viewModelScope.launch {
            when(val response = repository.insertCharacter(character, localDataSource)){
                is ResultData.Success -> {
                    if (response.data) {
                        _favoriteMutableLiveData.postValue(true)
                    }
                    else{
                        _favoriteMutableLiveData.postValue(false)
                    }
                }
                is ResultData.Error -> {
                    _errorString.postValue(response.exception.message)
                }
            }
        }
    }

    fun getFavorite(id: Int , localDataSource: LocalDataSource){
        viewModelScope.launch {
            when(val response = repository.getCharacter(id, localDataSource)){
                is ResultData.Success ->{
                    if (response.data.id == id){
                        _favoriteMutableLiveData.postValue(true)
                    }
                    else {
                        _favoriteMutableLiveData.postValue(false)
                    }
                }
            }
        }
    }

    fun deleteFavorite(character: CharacterModel, localDataSource: LocalDataSource){
        viewModelScope.launch {
            when(val response = repository.deleteCharacter(character, localDataSource)){
                is ResultData.Success ->{
                    if (response.data) {
                        _favoriteMutableLiveData.postValue(false)
                    }
                    else{
                        _favoriteMutableLiveData.postValue(true)
                    }
                }
                is ResultData.Error ->{
                    _errorString.postValue(response.exception.message)
                }
            }
        }
    }
}