package com.melvin.tvseries.home.presentation.list.viewmodel

import androidx.paging.PagingData
import com.melvin.tvseries.MainCoroutineRule
import com.melvin.tvseries.home.domain.SeriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: SeriesRepository = mockk()
    
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        coEvery { repository.getSeries() } returns flowOf(PagingData.empty())

        viewModel = HomeViewModel(repository)

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
    }
    
    @Test
    fun `validate event UiEventHandled`() {
        viewModel.onEvent(HomeEvent.UiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnSearchClicked`() {
        viewModel.onEvent(HomeEvent.OnSearchClicked)

        assertEquals(HomeUiEvent.NavigateToSearch, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event ShowError`() {
        viewModel.onEvent(HomeEvent.ShowError("error"))

        assertEquals(HomeUiEvent.ShowError("error"), viewModel.state.uiEvent)
    }
}