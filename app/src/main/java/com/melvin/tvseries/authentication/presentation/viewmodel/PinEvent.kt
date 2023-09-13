package com.melvin.tvseries.authentication.presentation.viewmodel

sealed class PinEvent {
    data class OnTextChanged(val text: String) : PinEvent()
    object OnButtonClicked : PinEvent()
    object OnUiEventHandled : PinEvent()
}
