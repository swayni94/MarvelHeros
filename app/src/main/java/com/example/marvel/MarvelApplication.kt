package com.example.marvel

import android.app.Application
import com.example.marvel.di.component.AppComponent
import com.example.marvel.di.component.DaggerAppComponent
import com.example.marvel.di.modules.AppModule
import com.example.marvel.di.modules.DatabaseModule

class MarvelApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}