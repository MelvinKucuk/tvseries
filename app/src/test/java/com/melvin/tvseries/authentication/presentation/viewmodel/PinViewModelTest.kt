package com.melvin.tvseries.authentication.presentation.viewmodel

import com.melvin.tvseries.authentication.domain.PinCache
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals

import org.junit.Before
import org.junit.Test

class PinViewModelTest {

    private val pinCache: PinCache = mockk()

    private lateinit var viewModel: PinViewModel

    @Before
    fun setUp() {
        every { pinCache.getPin() } returns 1234

        viewModel = PinViewModel(pinCache)
    }

    @Test
    fun `validate state text change on text input`() {
        viewModel.onEvent(PinEvent.OnTextChanged("12"))

        assertEquals("12", viewModel.state.text)
    }

    @Test
    fun `validate state text change on text input with more than 4 digits`() {
        viewModel.onEvent(PinEvent.OnTextChanged("12345"))

        assertEquals("1234", viewModel.state.text)
    }

    @Test
    fun `validate state is not setup when pin is saved`() {
        assertEquals(false, viewModel.state.isSetup)
    }

    @Test
    fun `validate state is setup when pin is saved`() {
        every { pinCache.getPin() } returns 0

        viewModel = PinViewModel(pinCache)

        assertEquals(true, viewModel.state.isSetup)
    }

    @Test
    fun `validate event OnUiEventHandled`() {
        viewModel.onEvent(PinEvent.OnUiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate pin saved on button clicked on setup mode`() {
        every { pinCache.getPin() } returns 0
        every { pinCache.savePin(any()) } returns Unit
        viewModel = PinViewModel(pinCache)
        viewModel.onEvent(PinEvent.OnTextChanged("1234"))

        viewModel.onEvent(PinEvent.OnButtonClicked)

        verify(exactly = 1) { pinCache.savePin(any()) }
        assertEquals(PinUiEvent.NavigateBack, viewModel.state.uiEvent)
    }

    @Test
    fun `validate invalid pin on button clicked on setup mode`() {
        viewModel.onEvent(PinEvent.OnTextChanged("4444"))

        viewModel.onEvent(PinEvent.OnButtonClicked)

        assertEquals(PinUiEvent.ShowInvalidPin, viewModel.state.uiEvent)
    }

    @Test
    fun `validate valid pin on button clicked on setup mode`() {
        viewModel.onEvent(PinEvent.OnTextChanged("1234"))

        viewModel.onEvent(PinEvent.OnButtonClicked)

        assertEquals(PinUiEvent.NavigateHome, viewModel.state.uiEvent)
    }
}