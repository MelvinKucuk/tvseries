package com.melvin.tvseries.home.presentation.detail.episode.viewmodel

data class EpisodeDetailState(
    val name: String = "",
    val summary: String = "",
    val image: String = "",
    val season: String = "",
    val episode: String = "",
    val uiEvent: EpisodeDetailUiEvent? = null
)
