package com.melvin.tvseries.authentication.presentation.viewmodel

data class PinState(
    val text: String = "",
    val uiEvent: PinUiEvent? = null,
    val isSetup: Boolean = true
)
