package com.example.marvel.di.component

import android.content.Context
import com.example.marvel.data.db.AppDatabase
import com.example.marvel.di.modules.AppModule
import com.example.marvel.di.modules.DatabaseModule
import com.example.marvel.di.modules.RepositoryModule
import com.example.marvel.ui.activity.CharacterDetailActivity
import com.example.marvel.ui.activity.CharacterListActivity
import com.example.marvel.ui.activity.SplashActivity
import com.example.marvel.ui.fragment.CharacterDetailFragment
import com.example.marvel.ui.fragment.CharacterListFragment
import com.example.marvel.ui.fragment.FavoriteListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    RepositoryModule::class
])
interface AppComponent {

    fun context(): Context

    fun inject(splashActivity: SplashActivity)
    fun inject(characterListActivity: CharacterListActivity)
    fun inject(characterDetailActivity: CharacterDetailActivity)
    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterDetailFragment: CharacterDetailFragment)
    fun inject(favoriteListFragment: FavoriteListFragment)
}