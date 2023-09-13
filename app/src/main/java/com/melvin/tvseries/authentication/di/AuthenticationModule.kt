package com.melvin.tvseries.authentication.di

import com.melvin.tvseries.authentication.data.PinCacheImpl
import com.melvin.tvseries.authentication.domain.PinCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Binds
    abstract fun providesPinCache(pinCache: PinCacheImpl): PinCache
}