package com.melvin.tvseries.home.data.model

import com.squareup.moshi.Json

data class SeasonDto(
    @Json(name = "number") val number: Int?,
    @Json(name = "episodeOrder") val episodeCount: Int?,
)
