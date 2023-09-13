package com.melvin.tvseries.home.presentation.detail.series.viewmodel

sealed class SeriesDetailUiEvent {
    data class ShowError(val errorMessage: String) : SeriesDetailUiEvent()
}
