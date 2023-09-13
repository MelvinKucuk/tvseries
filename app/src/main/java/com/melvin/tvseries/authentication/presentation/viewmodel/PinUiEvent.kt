package com.melvin.tvseries.authentication.presentation.viewmodel

sealed class PinUiEvent {
    object NavigateBack : PinUiEvent()
    object NavigateHome : PinUiEvent()
    object ShowInvalidPin : PinUiEvent()
}
