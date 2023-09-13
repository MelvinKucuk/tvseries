package com.melvin.tvseries.home.presentation.search.viewmodel

import com.melvin.tvseries.home.domain.Series

data class SearchState(
    val text: String = "",
    val series: List<Series> = listOf(),
    val isLoading: Boolean = false,
    val uiEvent: SearchUiEvent? = null
)
