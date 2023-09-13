package com.melvin.tvseries.home.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.melvin.tvseries.R

@Composable
fun SeriesHeader(
    image: String,
    genres: List<String>,
    summary: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(height = 200.dp, width = 100.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.image),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.size(8.dp))

        Column {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(genres) { genre ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Gray,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(6.dp)
                    ) {
                        Text(text = genre)
                    }
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(text = summary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeriesHeaderPreview() {
    SeriesHeader(
        image = "",
        genres = listOf("Action", "Adventure", "Crime"),
        summary = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
    )
}