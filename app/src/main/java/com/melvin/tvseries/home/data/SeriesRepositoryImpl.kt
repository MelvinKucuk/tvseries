package com.melvin.tvseries.home.data

import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.core.data.safeApiCall
import com.melvin.tvseries.home.data.model.toDomain
import com.melvin.tvseries.home.domain.Series
import com.melvin.tvseries.home.domain.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val service: SeriesService
): SeriesRepository {

    override suspend fun getSeries(nextPage: Int): Resource<List<Series>> {
        val result = safeApiCall {
            service.getSeries(nextPage)
        }

        return when (result) {
            is Resource.Success -> {
                Resource.Success(result.data.map { it.toDomain() })
            }
            is Resource.Error -> Resource.Error(result.errorMessage)
        }
    }
}