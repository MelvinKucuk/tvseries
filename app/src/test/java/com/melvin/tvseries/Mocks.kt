package com.melvin.tvseries

import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.home.domain.Episode
import com.melvin.tvseries.home.domain.Image
import com.melvin.tvseries.home.domain.Schedule
import com.melvin.tvseries.home.domain.Season
import com.melvin.tvseries.home.domain.Series

val series = listOf(
    Series(
        id = 123,
        name = "Arrow",
        genres = listOf("Action", "Drama"),
        schedule = Schedule(
            time = "20:00",
            days = listOf("Monday", "Tuesday")
        ),
        image = Image(
            medium = "url",
            original = "bigUrl"
        ),
        summary = "A great summary",
        seasons = listOf(
            Season(
                number = 1,
                episodeCount = 13
            ),
            Season(
                number = 2,
                episodeCount = 10
            )
        ),
    )
)

val seriesSuccess = Resource.Success(
    series
)

val episode = Episode(
    name = "Arrow 1",
    summary = "Great summary",
    image = "big url",
    seasonNumber = 1,
    episodeNumber = 1,
)

val episodeSuccess = Resource.Success(episode)

val episodeError = Resource.Error<Episode>("error")