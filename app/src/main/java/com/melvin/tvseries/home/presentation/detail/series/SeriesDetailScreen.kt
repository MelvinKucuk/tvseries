package com.melvin.tvseries.home.presentation.detail.series

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.tvseries.home.presentation.components.SeasonRow
import com.melvin.tvseries.home.presentation.components.SeriesHeader
import com.melvin.tvseries.home.presentation.detail.series.viewmodel.SeriesDetailEvent
import com.melvin.tvseries.home.presentation.detail.series.viewmodel.SeriesDetailState

@Composable
fun SeriesDetailScreen(
    state: SeriesDetailState,
    onEvent: (SeriesDetailEvent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(24.dp)
        ) {
            item {
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = state.name,
                style = MaterialTheme.typography.titleLarge
            )
            }

            item {
                SeriesHeader(
                    image = state.image,
                    genres = state.genres,
                    summary = state.summary
                )
            }

            item {
                Text(text = "Schedule: ${state.schedule.time}")
                Text(text = "In days")
                Row {
                    state.schedule.days.forEach {
                        Text(text = it)
                    }
                }
            }

            items(state.seasons) {
                SeasonRow(
                    seasonNumber = it.number,
                    episodeCount = it.episodeCount
                ) { seasonNumber, episodeNumber ->

                }
            }
        }
    }
}

@Preview
@Composable
fun SeriesDetailScreenPreview() {
    SeriesDetailScreen(SeriesDetailState()) {}
}