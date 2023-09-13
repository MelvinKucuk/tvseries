package com.melvin.tvseries.home.presentation.search.viewmodel

sealed class SearchUiEvent {
    data class NavigateToSeriesDetail(val seriesId: Int) : SearchUiEvent()
    data class ShowError(val errorMessage: String) : SearchUiEvent()
    object NavigateBack: SearchUiEvent()
}
