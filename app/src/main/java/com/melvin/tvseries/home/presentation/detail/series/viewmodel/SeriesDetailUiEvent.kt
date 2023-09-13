package com.melvin.tvseries.home.presentation.detail.series.viewmodel

sealed class SeriesDetailUiEvent {
    data class ShowError(val errorMessage: String) : SeriesDetailUiEvent()
    object NavigateBack: SeriesDetailUiEvent()
    data class NavigateToEpisodeDetail(
        val seriesId: Int,
        val seasonNumber: Int,
        val episodeNumber: Int
    ): SeriesDetailUiEvent()
}
