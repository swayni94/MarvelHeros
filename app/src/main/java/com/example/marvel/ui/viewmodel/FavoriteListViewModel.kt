package com.example.marvel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.data.service.local.LocalDataSource
import com.example.marvel.data.service.repository.Repository
import com.example.marvel.data.until.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _characterMutableLiveData = MutableLiveData<List<CharacterModel>>()
    val characterInfoLiveData : LiveData<List<CharacterModel>> get() = _characterMutableLiveData

    private val _errorString = MutableLiveData<String>()
    val errorString : LiveData<String> get() = _errorString

    fun getFavoriteList(localDataSource: LocalDataSource){
        viewModelScope.launch {
            when(val response = repository.getPopularCharacterList(localDataSource)){
                is ResultData.Success ->{
                    _characterMutableLiveData.postValue(response.data)
                }
                is ResultData.Error ->{
                    _errorString.postValue(response.exception.message)
                }
            }
        }
    }
}