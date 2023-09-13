package com.melvin.tvseries.home.presentation.detail.series.viewmodel

import com.melvin.tvseries.home.domain.Schedule
import com.melvin.tvseries.home.domain.Season

data class SeriesDetailState(
    val name: String = "",
    val image: String = "",
    val genres: List<String> = listOf(),
    val schedule: Schedule = Schedule(),
    val seasons: List<Season> = listOf(),
    val summary: String = "",
    val uiEvent: SeriesDetailUiEvent? = null
)
