package com.melvin.tvseries.home.presentation.viewmodel

import com.melvin.tvseries.home.domain.Series

data class HomeState(
    val series: List<Series> = listOf(),
    val errorMessage: String? = null
)
