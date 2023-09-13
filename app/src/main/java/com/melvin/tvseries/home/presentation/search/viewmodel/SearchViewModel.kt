package com.melvin.tvseries.home.presentation.search.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.home.domain.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SeriesRepository
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnTextChanged -> {
                state = state.copy(
                    text = event.text,
                    isLoading = true
                )

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(debounceDelay)

                    state = when (val result = repository.searchSeries(state.text)) {
                        is Resource.Success -> {
                            state.copy(
                                series = result.data,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            state.copy(
                                uiEvent = SearchUiEvent.ShowError(result.errorMessage),
                                isLoading = false
                            )
                        }
                    }
                }
            }

            is SearchEvent.NavigateToSeriesDetail -> {
                event.seriesId?.let {
                    state = state.copy(uiEvent = SearchUiEvent.NavigateToSeriesDetail(it))
                }
            }

            SearchEvent.UiEventHandled -> state = state.copy(uiEvent = null)
            SearchEvent.OnBackClicked -> state = state.copy(uiEvent = SearchUiEvent.NavigateBack)
        }
    }

    companion object {
        private const val debounceDelay = 1000L
    }

}