package com.melvin.tvseries.home.presentation.detail.episode.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.melvin.tvseries.MainCoroutineRule
import com.melvin.tvseries.Routes
import com.melvin.tvseries.episode
import com.melvin.tvseries.episodeError
import com.melvin.tvseries.episodeSuccess
import com.melvin.tvseries.home.domain.SeriesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EpisodeDetailViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: SeriesRepository = mockk()

    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: EpisodeDetailViewModel

    @Before
    fun setUp() {
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SERIES_ID) } returns 1
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SEASON_NUMBER) } returns 1
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.EPISODE_NUMBER) } returns 1

        coEvery { repository.getEpisode(any(),any(),any()) } returns episodeSuccess

        viewModel = EpisodeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `validate viewmodel init with success response`() {
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            EpisodeDetailState().copy(
                name = episode.name,
                summary = episode.summary,
                image = episode.image,
                season = episode.seasonNumber.toString(),
                episode = episode.episodeNumber.toString(),
            ),
            viewModel.state
        )
    }

    @Test
    fun `validate viewmodel init with error response`() {
        coEvery { repository.getEpisode(any(),any(),any()) } returns episodeError
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            EpisodeDetailUiEvent.ShowError(episodeError.errorMessage),
            viewModel.state.uiEvent
        )
    }

    @Test
    fun `validate viewmodel init with null series id`() {
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SERIES_ID) } returns null

        viewModel = EpisodeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            EpisodeDetailUiEvent.ShowError("Invalid series ID"),
            viewModel.state.uiEvent
        )
    }

    @Test
    fun `validate viewmodel init with null episode number`() {
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.EPISODE_NUMBER) } returns null

        viewModel = EpisodeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            EpisodeDetailUiEvent.ShowError("Invalid episode number"),
            viewModel.state.uiEvent
        )
    }

    @Test
    fun `validate viewmodel init with null season number`() {
        every { savedStateHandle.get<Int>(Routes.EpisodeDetailScreen.SEASON_NUMBER) } returns null

        viewModel = EpisodeDetailViewModel(
            repository = repository,
            savedStateHandle = savedStateHandle
        )

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(
            EpisodeDetailUiEvent.ShowError("Invalid season number"),
            viewModel.state.uiEvent
        )
    }

    @Test
    fun `validate event ShowError`() {
        viewModel.onEvent(EpisodeDetailEvent.ShowError("error"))

        assertEquals(EpisodeDetailUiEvent.ShowError("error"), viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnUiEventHandled`() {
        viewModel.onEvent(EpisodeDetailEvent.OnUiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnBackClicked`() {
        viewModel.onEvent(EpisodeDetailEvent.OnBackClicked)

        assertEquals(EpisodeDetailUiEvent.NavigateBack, viewModel.state.uiEvent)
    }
}