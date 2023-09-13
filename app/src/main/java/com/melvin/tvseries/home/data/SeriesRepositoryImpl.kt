package com.melvin.tvseries.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.core.data.safeApiCall
import com.melvin.tvseries.home.data.model.toDomain
import com.melvin.tvseries.home.domain.Episode
import com.melvin.tvseries.home.domain.Series
import com.melvin.tvseries.home.domain.SeriesRepository
import com.melvin.tvseries.home.presentation.list.pagination.SeriesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val service: SeriesService
) : SeriesRepository {

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

    override suspend fun getSeriesById(seriesId: Int, onResult: (Resource<Series>) -> Unit) {
        supervisorScope {
            val series = safeApiCall {
                service.getSeriesById(seriesId)
            }

            val seasons = safeApiCall {
                service.getSeasonsBySeriesId(seriesId)
            }

            onResult(
                when (series) {
                    is Resource.Success -> {
                        when (seasons) {
                            is Resource.Success -> Resource.Success(series.data.toDomain().copy(
                                seasons = seasons.data.map { it.toDomain() }
                            ))

                            is Resource.Error -> Resource.Error(seasons.errorMessage)
                        }
                    }

                    is Resource.Error -> Resource.Error(series.errorMessage)
                }
            )
        }
    }

    override suspend fun getEpisode(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Resource<Episode> {
        val result = safeApiCall {
            service.getEpisode(
                seriesId = seriesId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber
            )
        }

        return when (result) {
            is Resource.Success -> Resource.Success(result.data.toDomain())
            is Resource.Error -> Resource.Error(result.errorMessage)
        }
    }
}