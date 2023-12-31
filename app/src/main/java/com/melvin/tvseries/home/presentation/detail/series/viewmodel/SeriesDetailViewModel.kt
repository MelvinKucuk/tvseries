package com.melvin.tvseries.home.presentation.detail.series.viewmodel

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
class SeriesDetailViewModel @Inject constructor(
    private val repository: SeriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(SeriesDetailState())
        private set

    init {
        val seriesId = savedStateHandle.get<Int>(Routes.SeriesDetailScreen.SERIES_ID)
        if (seriesId == null) {
            onEvent(SeriesDetailEvent.ShowError("Invalid ID"))
        } else {
            viewModelScope.launch {
                repository.getSeriesById(seriesId) {
                    when (val result = it) {
                        is Resource.Success -> with(result.data) {
                            state = state.copy(
                                id = seriesId,
                                name = name,
                                image = image.original,
                                genres = genres,
                                summary = summary,
                                schedule = schedule,
                                seasons = seasons
                            )
                        }

                        is Resource.Error -> onEvent(SeriesDetailEvent.ShowError(result.errorMessage))
                    }
                }
            }
        }
    }

    fun onEvent(event: SeriesDetailEvent) {
        state = when (event) {
            is SeriesDetailEvent.OnEpisodeClick -> {
                state.copy(
                    uiEvent = SeriesDetailUiEvent.NavigateToEpisodeDetail(
                        seriesId = state.id,
                        seasonNumber = event.seasonNumber,
                        episodeNumber = event.episodeNumber
                    )
                )
            }

            SeriesDetailEvent.OnUiEventHandled -> {
                state.copy(uiEvent = null)
            }

            is SeriesDetailEvent.ShowError ->
                state.copy(uiEvent = SeriesDetailUiEvent.ShowError(event.errorMessage))

            SeriesDetailEvent.OnBackClicked -> {
                state.copy(uiEvent = SeriesDetailUiEvent.NavigateBack)
            }
        }
    }
}