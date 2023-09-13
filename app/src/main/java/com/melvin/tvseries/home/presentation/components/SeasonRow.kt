package com.melvin.tvseries.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.tvseries.ui.theme.Purple40

@Composable
fun SeasonRow(
    seasonNumber: Int,
    episodeCount: Int,
    modifier: Modifier = Modifier,
    onEpisodeClick: (seasonNumber: Int, episodeNumber: Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = "Season $seasonNumber",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.size(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(episodeCount) {
                Box(
                    modifier = Modifier
                        .background(Purple40, CircleShape)
                        .size(50.dp)
                        .clickable { onEpisodeClick(
                            seasonNumber,
                            it + 1
                        ) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${it + 1}",
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeasonRowPreview() {
    SeasonRow(seasonNumber = 1, episodeCount = 13) { _, _ -> }
}