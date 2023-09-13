package com.melvin.tvseries.home.data.model

import com.squareup.moshi.Json

data class EpisodeDto(
    @Json(name = "name")val name: String?,
    @Json(name = "summary")val summary: String?,
    @Json(name = "image")val image: ImageDto?,
    @Json(name = "season")val season: Int?,
    @Json(name = "episode")val episode: Int?
)
