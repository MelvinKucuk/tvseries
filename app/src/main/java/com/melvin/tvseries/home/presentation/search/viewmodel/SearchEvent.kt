package com.melvin.tvseries.home.presentation.search.viewmodel

sealed class SearchEvent {
    data class OnTextChanged(val text: String) : SearchEvent()
    data class NavigateToSeriesDetail(val seriesId: Int?) : SearchEvent()
    object UiEventHandled: SearchEvent()
    object OnBackClicked: SearchEvent()
}