package com.example.movieappv4.ui.di

import com.example.movieappv4.data.MovieDataRepository
import com.example.movieappv4.data.remote.MovieRemoteImpl
import com.example.movieappv4.data.source.MovieRemote
import com.example.movieappv4.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(data: MovieDataRepository): MovieRepository {
        return data
    }

    @Singleton
    @Provides
    fun provideDataSource(dataSourceRemote: MovieRemoteImpl): MovieRemote {
        return dataSourceRemote
    }
}
