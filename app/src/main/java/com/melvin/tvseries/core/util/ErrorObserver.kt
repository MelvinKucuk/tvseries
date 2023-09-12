package com.melvin.tvseries.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ObserveError(errorMessage: String?, onErrorShown: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage != null) {
            makeToast(context, errorMessage)
            onErrorShown()
        }
    }
}