package com.melvin.tvseries.home.presentation.list.viewmodel

sealed class HomeUiEvent {
    object NavigateToSearch : HomeUiEvent()
    data class NavigateToSeriesDetail(val seriesId: Int) : HomeUiEvent()
}
