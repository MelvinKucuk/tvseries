package com.melvin.tvseries.home.presentation.list.viewmodel

import com.melvin.tvseries.home.domain.Series

sealed class HomeEvent {
    data class OnSeriesClicked(val series: Series?): HomeEvent()
    data class ShowError(val errorMessage: String): HomeEvent()
    object OnSearchClicked: HomeEvent()
    object ErrorShown: HomeEvent()
    object SearchNavigated: HomeEvent()
}
