package com.melvin.tvseries.home.presentation.viewmodel

import com.melvin.tvseries.home.domain.Series

sealed class HomeEvent {
    data class OnSeriesClicked(val series: Series?): HomeEvent()
    data class ShowError(val errorMessage: String): HomeEvent()
    object ErrorShown: HomeEvent()
    object OnLastItemReached: HomeEvent()
}
