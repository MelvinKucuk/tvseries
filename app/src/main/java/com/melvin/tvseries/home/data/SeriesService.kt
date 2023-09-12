package com.melvin.tvseries.home.data

import com.melvin.tvseries.home.data.model.SearchResponse
import com.melvin.tvseries.home.data.model.SeriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesService {

    @GET("shows")
    suspend fun getSeries(@Query("page") page: Int): Response<List<SeriesDto>>

    @GET("search/shows")
    suspend fun searchSeries(@Query("q") query: String): Response<List<SearchResponse>>
}