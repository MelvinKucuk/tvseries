package com.melvin.tvseries.home.presentation.viewmodel

import com.melvin.tvseries.home.domain.Series

sealed class HomeEvent {
    data class OnSeriesClicked(val series: Series): HomeEvent()
    object ErrorShown: HomeEvent()
}
