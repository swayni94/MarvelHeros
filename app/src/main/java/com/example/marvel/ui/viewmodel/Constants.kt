package com.example.marvel.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.data.model.Character


var _character : Character? = null

var favoriteMutableLiveData = MutableLiveData<Boolean>()
val favoriteLiveData get() = favoriteMutableLiveData

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider)[VM::class.java]