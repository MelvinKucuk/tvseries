package com.melvin.tvseries.authentication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melvin.tvseries.R
import com.melvin.tvseries.authentication.presentation.viewmodel.PinEvent
import com.melvin.tvseries.authentication.presentation.viewmodel.PinState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinScreen(
    state: PinState,
    onEvent: (PinEvent) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(44.dp))

            Text(
                text = stringResource(R.string.set_up_4_digit_pin),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.size(12.dp))

            TextField(
                value = state.text,
                onValueChange = { onEvent(PinEvent.OnTextChanged(it)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.size(12.dp))

            Button(onClick = { onEvent(PinEvent.OnButtonClicked) }) {
                Text(
                    text = if (state.isSetup) {
                        stringResource(R.string.save)
                    } else {
                        stringResource(R.string.accept)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PinScreenPreview() {
    PinScreen(state = PinState()) {}
}