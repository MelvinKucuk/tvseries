package com.melvin.tvseries.home.presentation.detail.episode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.melvin.tvseries.R
import com.melvin.tvseries.home.presentation.detail.episode.viewmodel.EpisodeDetailEvent
import com.melvin.tvseries.home.presentation.detail.episode.viewmodel.EpisodeDetailState

@Composable
fun EpisodeDetailScreen(
    state: EpisodeDetailState,
    onEvent: (EpisodeDetailEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            IconButton(
                onClick = {
                    onEvent(EpisodeDetailEvent.OnBackClicked)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                AsyncImage(
                    modifier = Modifier
                        .height(200.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.image),
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = state.summary,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = "Season ${state.season}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Episode ${state.episode}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview
@Composable
fun EpisodeDetailScreenPreview() {
    EpisodeDetailScreen(state = EpisodeDetailState()) {}
}