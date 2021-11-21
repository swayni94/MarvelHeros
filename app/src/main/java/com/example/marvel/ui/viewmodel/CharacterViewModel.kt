package com.example.marvel.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.model.CharacterDataContainer
import com.example.marvel.data.service.repository.Repository
import com.example.marvel.data.until.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _characterMutableLiveData = MutableLiveData<CharacterDataContainer>()
    val characterInfoLiveData : LiveData<CharacterDataContainer> get() = _characterMutableLiveData

    private val _errorString = MutableLiveData<String>()
    val errorString : LiveData<String> get() = _errorString

    fun getCharacters(offset: Int, limit:Int){
        viewModelScope.launch{
            when(val response = repository.getCharacters(offset, limit)){
                is ResultData.Success ->{
                    _characterMutableLiveData.postValue(response.data.data)
                }
                is ResultData.Error ->{
                    _errorString.postValue(response.exception.message)
                }
            }
        }
    }
}