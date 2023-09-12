package com.melvin.tvseries.home.data.model

import com.squareup.moshi.Json

data class SeriesDto(
    @Json(name = "id") var id: Int? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "genres") var genres: List<String?> = listOf(),
    @Json(name = "schedule") var schedule: ScheduleDto? = ScheduleDto(),
    @Json(name = "image") var image: ImageDto? = ImageDto(),
    @Json(name = "summary") var summary: String? = null,
)


data class ImageDto(
    @Json(name = "medium") var medium: String? = null,
    @Json(name = "original") var original: String? = null
)

data class ScheduleDto(
    @Json(name = "time") var time: String? = null,
    @Json(name = "days") var days: List<String?> = listOf()
)
