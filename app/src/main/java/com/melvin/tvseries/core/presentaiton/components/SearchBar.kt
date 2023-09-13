package com.melvin.tvseries.core.presentaiton.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.melvin.tvseries.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchClick: () -> Unit,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(R.string.tv_series))
        },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
            IconButton(onClick = { onLockClick() }) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.security)
                )
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        onSearchClick = {},
        onLockClick = {}
    )
}