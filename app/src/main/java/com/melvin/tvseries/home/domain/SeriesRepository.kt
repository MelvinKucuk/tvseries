package com.melvin.tvseries.home.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeries(): Flow<PagingData<Series>>
}