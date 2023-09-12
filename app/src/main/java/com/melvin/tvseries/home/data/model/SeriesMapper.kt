package com.melvin.tvseries.home.data.model

import com.melvin.tvseries.home.domain.Image
import com.melvin.tvseries.home.domain.Schedule
import com.melvin.tvseries.home.domain.Series

fun SeriesDto.toDomain() =
    Series(
        id = id,
        name = name,
        genres = genres,
        schedule = schedule?.toDomain(),
        image = image?.toDomain(),
        summary = summary
    )

fun ImageDto.toDomain() =
    Image(
        medium = medium,
        original = original
    )

fun ScheduleDto.toDomain() =
    Schedule(
        time = time,
        days = days
    )