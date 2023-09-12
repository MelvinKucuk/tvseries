package com.melvin.tvseries.home.presentation.viewmodel

import androidx.paging.PagingData
import com.melvin.tvseries.home.domain.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val series: Flow<PagingData<Series>> = flowOf(),
    val errorMessage: String? = null
)
