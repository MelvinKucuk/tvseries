package com.melvin.tvseries.home.data.model

import com.melvin.tvseries.home.domain.Image
import com.melvin.tvseries.home.domain.Schedule
import com.melvin.tvseries.home.domain.Season
import com.melvin.tvseries.home.domain.Series

fun SeriesDto.toDomain() =
    Series(
        id = id ?: 0,
        name = name ?: "",
        genres = genres ?: listOf(),
        schedule = schedule.toDomain(),
        image = image.toDomain(),
        summary = summary ?: ""
    )

fun ImageDto?.toDomain() =
    Image(
        medium = this?.medium ?: "",
        original = this?.original ?: ""
    )

fun ScheduleDto?.toDomain() =
    Schedule(
        time = this?.time ?: "",
        days = this?.days ?: listOf()
    )

fun SeasonDto.toDomain() =
    Season(
        number = number ?: 0,
        episodeCount = episodeCount ?: 0
    )