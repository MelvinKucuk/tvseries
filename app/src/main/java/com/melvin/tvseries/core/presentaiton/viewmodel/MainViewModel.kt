package com.melvin.tvseries.core.presentaiton.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.melvin.tvseries.authentication.domain.PinCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    pinCache: PinCache
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        val pin = pinCache.getPin()
        state = state.copy(shouldNavigateToPin = pin != 0)
    }
}