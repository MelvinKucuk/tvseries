package com.melvin.tvseries.home.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.melvin.tvseries.core.presentaiton.components.SearchBar
import com.melvin.tvseries.home.presentation.components.SeriesCard
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeEvent
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            SearchBar {
                onEvent(HomeEvent.OnSearchClicked)
            }
        }
    ) { paddingValues ->
        val series = state.series.collectAsLazyPagingItems()
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = series.itemCount,
            ) { index ->
                SeriesCard(
                    title = series[index]?.name ?: "",
                    image = series[index]?.image?.medium ?: ""
                ) {
                    onEvent(HomeEvent.OnSeriesClicked(series[index]))
                }
            }

            when (val paginationState = series.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    onEvent(HomeEvent.ShowError(paginationState.error.message ?: ""))
                }

                is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }
                }

                else -> {}
            }

            when (val paginationState = series.loadState.append) { // Pagination
                is LoadState.Error -> {
                    onEvent(HomeEvent.ShowError(paginationState.error.message ?: ""))
                }

                is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }
                }

                else -> {}
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(HomeState()) {}
}