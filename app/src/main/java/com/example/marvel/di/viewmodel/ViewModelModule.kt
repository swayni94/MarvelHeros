package com.example.marvel.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.ui.viewmodel.CharacterViewModel
import com.example.marvel.ui.viewmodel.ComicViewModel
import com.example.marvel.ui.viewmodel.FavoriteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ComicViewModel::class)
    abstract fun bindComicViewModel(comicViewModel: ComicViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    abstract fun bindCharacterViewModel(characterViewModel: CharacterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteListViewModel::class)
    abstract fun bindFavoriteListViewModel(favoriteListViewModel: FavoriteListViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}