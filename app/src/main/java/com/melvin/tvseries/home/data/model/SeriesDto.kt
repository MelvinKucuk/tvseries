package com.melvin.tvseries.home.data.model

import com.squareup.moshi.Json

data class SeriesDto(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "genres") val genres: List<String>? = listOf(),
    @Json(name = "schedule") val schedule: ScheduleDto? = ScheduleDto(),
    @Json(name = "image") val image: ImageDto? = ImageDto(),
    @Json(name = "summary") val summary: String? = null,
)


data class ImageDto(
    @Json(name = "medium") val medium: String? = null,
    @Json(name = "original") val original: String? = null
)

data class ScheduleDto(
    @Json(name = "time") val time: String? = null,
    @Json(name = "days") val days: List<String>? = listOf()
)
