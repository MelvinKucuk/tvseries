package com.melvin.tvseries.home.data

import com.melvin.tvseries.home.data.model.EpisodeDto
import com.melvin.tvseries.home.data.model.SearchResponse
import com.melvin.tvseries.home.data.model.SeasonDto
import com.melvin.tvseries.home.data.model.SeriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {

    @GET("shows")
    suspend fun getSeries(@Query("page") page: Int): Response<List<SeriesDto>>

    @GET("search/shows")
    suspend fun searchSeries(@Query("q") query: String): Response<List<SearchResponse>>

    @GET("shows/{seriesId}")
    suspend fun getSeriesById(@Path("seriesId") seriesId: Int): Response<SeriesDto>

    @GET("shows/{seriesId}/seasons")
    suspend fun getSeasonsBySeriesId(@Path("seriesId") seriesId: Int): Response<List<SeasonDto>>

    @GET("shows/{seriesId}/episodebynumber")
    suspend fun getEpisode(
        @Path("seriesId") seriesId: Int,
        @Query("season") seasonNumber: Int,
        @Query("number") episodeNumber: Int,
    ): Response<EpisodeDto>
}