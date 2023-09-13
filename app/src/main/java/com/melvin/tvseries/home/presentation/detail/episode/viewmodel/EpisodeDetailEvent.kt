package com.melvin.tvseries.home.presentation.detail.episode.viewmodel

sealed class EpisodeDetailEvent {
    object OnBackClicked : EpisodeDetailEvent()
    object OnUiEventHandled : EpisodeDetailEvent()
    data class ShowError(val errorMessage: String) : EpisodeDetailEvent()
}
