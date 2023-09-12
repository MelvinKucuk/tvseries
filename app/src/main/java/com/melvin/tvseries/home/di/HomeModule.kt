package com.melvin.tvseries.home.di

import com.melvin.tvseries.home.data.SeriesRepositoryImpl
import com.melvin.tvseries.home.data.SeriesService
import com.melvin.tvseries.home.domain.SeriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    fun providesSeriesService(retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeBindsModule {

    @Binds
    abstract fun providesSeriesRepository(repository: SeriesRepositoryImpl): SeriesRepository
}