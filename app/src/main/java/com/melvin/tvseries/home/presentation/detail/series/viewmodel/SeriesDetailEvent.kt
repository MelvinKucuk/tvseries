package com.melvin.tvseries.home.presentation.detail.series.viewmodel

sealed class SeriesDetailEvent {
    data class OnEpisodeClick(
        val episodeNumber: Int,
        val seasonNumber: Int
    ): SeriesDetailEvent()
    data class ShowError(val errorMessage: String): SeriesDetailEvent()
    object OnUiEventHandled : SeriesDetailEvent()
    object OnBackClicked : SeriesDetailEvent()
}
