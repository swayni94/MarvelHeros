package com.example.marvel.data.until

sealed class ResultData<out T : Any>{
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
}