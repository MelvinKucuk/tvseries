package com.melvin.tvseries.home.presentation.detail.episode.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.tvseries.Routes
import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.home.domain.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: SeriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(EpisodeDetailState())
        private set

    init {
        val seriesId = savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SERIES_ID)
        val seasonNumber = savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SEASON_NUMBER)
        val episodeNumber = savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.EPISODE_NUMBER)

        if (seriesId == null) {
            onEvent(EpisodeDetailEvent.ShowError("Invalid series ID"))
        }

        if (seasonNumber == null) {
            onEvent(EpisodeDetailEvent.ShowError("Invalid season number"))
        }

        if (episodeNumber == null) {
            onEvent(EpisodeDetailEvent.ShowError("Invalid episode number"))
        }

        viewModelScope.launch {
            val result = repository.getEpisode(
                seriesId = seriesId!!,
                seasonNumber = seasonNumber!!,
                episodeNumber = episodeNumber!!
            )

            when (result) {
                is Resource.Success -> {
                    with(result.data) {
                        state = state.copy(
                            name = name,
                            summary = summary,
                            image = image,
                            season = seasonNumber.toString(),
                            episode = episodeNumber.toString(),
                        )
                    }
                }

                is Resource.Error -> onEvent(EpisodeDetailEvent.ShowError(result.errorMessage))
            }
        }

    }

    fun onEvent(event: EpisodeDetailEvent) {
        state = when (event) {
            EpisodeDetailEvent.OnBackClicked -> state.copy(uiEvent = EpisodeDetailUiEvent.NavigateBack)
            EpisodeDetailEvent.OnUiEventHandled -> state.copy(uiEvent = null)
            is EpisodeDetailEvent.ShowError ->
                state.copy(uiEvent = EpisodeDetailUiEvent.ShowError(event.errorMessage))
        }
    }
}