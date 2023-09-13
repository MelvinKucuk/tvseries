package com.melvin.tvseries.home.presentation.detail.episode.viewmodel

sealed class EpisodeDetailUiEvent {
    data class ShowError(val errorMessage: String) : EpisodeDetailUiEvent()
    object NavigateBack : EpisodeDetailUiEvent()
}
