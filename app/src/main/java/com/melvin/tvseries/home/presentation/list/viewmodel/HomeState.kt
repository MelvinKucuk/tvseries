package com.melvin.tvseries.home.presentation.list.viewmodel

import androidx.paging.PagingData
import com.melvin.tvseries.home.domain.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val series: Flow<PagingData<Series>> = flowOf(),
    val uiEvent: HomeUiEvent? = null
)
