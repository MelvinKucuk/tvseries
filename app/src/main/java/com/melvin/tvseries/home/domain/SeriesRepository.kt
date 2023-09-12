package com.melvin.tvseries.home.domain

import com.melvin.tvseries.core.data.Resource

interface SeriesRepository {
    suspend fun getSeries(nextPage: Int = 0): Resource<List<Series>>
}