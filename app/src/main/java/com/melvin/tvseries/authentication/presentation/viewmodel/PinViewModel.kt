package com.melvin.tvseries.authentication.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.melvin.tvseries.authentication.domain.PinCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val pinCache: PinCache
) : ViewModel() {

    var state by mutableStateOf(PinState())
        private set

    init {
        val pin = pinCache.getPin()
        if (pin != 0) {
            state = state.copy(isSetup = false)
        }
    }

    fun onEvent(event: PinEvent) {
        state = when (event) {
            is PinEvent.OnTextChanged -> state.copy(text = event.text.take(4))
            PinEvent.OnButtonClicked -> {
                if (state.isSetup) {
                    pinCache.savePin(state.text.toInt())
                    state.copy(uiEvent = PinUiEvent.NavigateBack)
                } else {
                    val pin = pinCache.getPin()
                    if (pin == state.text.toInt()) {
                        state.copy(uiEvent = PinUiEvent.NavigateHome)
                    } else {
                        state.copy(uiEvent = PinUiEvent.ShowInvalidPin)
                    }
                }
            }

            PinEvent.OnUiEventHandled -> state.copy(uiEvent = null)
        }
    }
}