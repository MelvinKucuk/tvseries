package com.melvin.tvseries.home.presentation.list.viewmodel


sealed class HomeEvent {
    data class OnSeriesClicked(val seriesId: Int?): HomeEvent()
    data class ShowError(val errorMessage: String): HomeEvent()
    object OnSearchClicked: HomeEvent()
    object UiEventHandled: HomeEvent()
    object OnLockClicked: HomeEvent()
}
