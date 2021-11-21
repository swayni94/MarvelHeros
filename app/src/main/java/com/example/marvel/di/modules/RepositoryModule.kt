package com.example.marvel.di.modules

import com.example.marvel.data.service.remote.RemoteDataSourceImpl
import com.example.marvel.data.service.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        val remoteDataSource = RemoteDataSourceImpl()
        return Repository(remoteDataSource)
    }
}