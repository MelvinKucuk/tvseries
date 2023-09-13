package com.melvin.tvseries.home.presentation.list.viewmodel

sealed class HomeUiEvent {
    object NavigateToSearch : HomeUiEvent()
    object NavigateToPin : HomeUiEvent()
    data class NavigateToSeriesDetail(val seriesId: Int) : HomeUiEvent()
    data class ShowError(val errorMessage: String) : HomeUiEvent()
}
