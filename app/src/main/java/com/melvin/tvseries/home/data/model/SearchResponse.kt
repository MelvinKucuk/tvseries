package com.melvin.tvseries.home.data.model

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "show") val series: SeriesDto
)
