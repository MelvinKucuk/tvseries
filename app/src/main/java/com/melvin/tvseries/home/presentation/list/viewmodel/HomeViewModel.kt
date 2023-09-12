package com.melvin.tvseries.home.presentation.list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.melvin.tvseries.home.domain.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SeriesRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                series = repository.getSeries().distinctUntilChanged().cachedIn(viewModelScope)
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        state = when (event) {
            is HomeEvent.OnSeriesClicked -> {
                state.copy()
            }

            HomeEvent.ErrorShown -> {
                state.copy(errorMessage = null)
            }

            is HomeEvent.ShowError -> {
                state.copy(errorMessage = event.errorMessage)
            }

            HomeEvent.SearchNavigated -> {
                state.copy(navigateToSearch = null)
            }

            HomeEvent.OnSearchClicked -> {
                state.copy(navigateToSearch = true)
            }
        }
    }
}