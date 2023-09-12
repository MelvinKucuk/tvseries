package com.melvin.tvseries.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.core.data.safeApiCall
import com.melvin.tvseries.home.data.model.toDomain
import com.melvin.tvseries.home.domain.Series
import com.melvin.tvseries.home.domain.SeriesRepository
import com.melvin.tvseries.home.presentation.list.pagination.SeriesPagingSource
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

    override suspend fun searchSeries(query: String): Resource<List<Series>> {
        val result = safeApiCall {
            service.searchSeries(query)
        }

        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data.map { it.series }.map { it.toDomain() })
            }
            is Resource.Error -> Resource.Error(result.errorMessage)
        }
    }
}