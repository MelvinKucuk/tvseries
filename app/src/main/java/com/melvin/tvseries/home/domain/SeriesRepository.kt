package com.melvin.tvseries.home.domain

import androidx.paging.PagingData
import com.melvin.tvseries.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    suspend fun getSeries(): Flow<PagingData<Series>>
    suspend fun searchSeries(query: String): Resource<List<Series>>
    suspend fun getSeriesById(seriesId: Int, onResult: (Resource<Series>) -> Unit)
}