package com.example.marvel.di.modules

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvel.data.constant.Database
import com.example.marvel.data.db.AppDatabase
import com.example.marvel.data.db.CharacterDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule (private val application: Application) {
    @Provides
    fun providesCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }


    @Provides
    fun provideAppDatabase(appDatabaseBuilder: RoomDatabase.Builder<AppDatabase?>) =
        appDatabaseBuilder.fallbackToDestructiveMigration().build()

    @Provides
    fun provideAppDatabaseBuilder() =
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            Database.NAME)
}