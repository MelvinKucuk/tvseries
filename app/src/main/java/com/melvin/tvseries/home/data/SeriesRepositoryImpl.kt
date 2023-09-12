package com.melvin.tvseries.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.melvin.tvseries.home.domain.Series
import com.melvin.tvseries.home.domain.SeriesRepository
import com.melvin.tvseries.home.presentation.pagination.SeriesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val service: SeriesService
): SeriesRepository {

    override suspend fun getSeries(): Flow<PagingData<Series>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                SeriesPagingSource(service)
            }
        ).flow
    }
}