package com.melvin.tvseries.home.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.tvseries.home.presentation.components.SearchComponent
import com.melvin.tvseries.home.presentation.components.SeriesCard
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchEvent
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchComponent(text = state.text) {
                onEvent(SearchEvent.OnTextChanged(it))
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.series) { series ->
                    SeriesCard(
                        title = series.name ?: "",
                        image = series.image?.medium ?: ""
                    ) {
                        //onEvent(HomeEvent.OnSeriesClicked(series[index]))
                    }
                }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(SearchState()) {}
}