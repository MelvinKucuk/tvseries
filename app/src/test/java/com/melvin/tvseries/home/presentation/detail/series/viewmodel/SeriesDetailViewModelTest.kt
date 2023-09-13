package com.melvin.tvseries.home.presentation.detail.series.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.melvin.tvseries.MainCoroutineRule
import com.melvin.tvseries.Routes
import com.melvin.tvseries.home.domain.SeriesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SeriesDetailViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: SeriesRepository = mockk()

    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: SeriesDetailViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<Int>(Routes.SeriesDetailScreen.SERIES_ID) } returns 1

        coEvery { repository.getSeriesById(any(), any()) } returns Unit

        viewModel = SeriesDetailViewModel(
            repository, savedStateHandle
        )
    }

    @Test
    fun `validate event OnEpisodeClick`() {
        viewModel.onEvent(
            SeriesDetailEvent.OnEpisodeClick(
                episodeNumber = 1,
                seasonNumber = 1
            )
        )

        assertEquals(
            SeriesDetailState().copy(
                uiEvent = SeriesDetailUiEvent.NavigateToEpisodeDetail(
                    seriesId = 0,
                    seasonNumber = 1,
                    episodeNumber = 1
                )
            ),
            viewModel.state
        )
    }

    @Test
    fun `validate event ShowError`() {
        viewModel.onEvent(SeriesDetailEvent.ShowError("error"))

        assertEquals(SeriesDetailUiEvent.ShowError("error"), viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnUiEventHandled`() {
        viewModel.onEvent(SeriesDetailEvent.OnUiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnBackClicked`() {
        viewModel.onEvent(SeriesDetailEvent.OnBackClicked)

        assertEquals(SeriesDetailUiEvent.NavigateBack, viewModel.state.uiEvent)
    }
}