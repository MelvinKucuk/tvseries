package com.melvin.tvseries.home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melvin.tvseries.core.data.Resource
import com.melvin.tvseries.home.domain.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SeriesRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state = when (val result = repository.getSeries()) {
                is Resource.Success -> {
                    state.copy(
                        series = result.data
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSeriesClicked -> {
                // TODO
            }

            HomeEvent.ErrorShown -> {
                state = state.copy(errorMessage = null)
            }
        }
    }
}