package com.melvin.tvseries.home.presentation.search.viewmodel

import com.melvin.tvseries.MainCoroutineRule
import com.melvin.tvseries.home.domain.SeriesRepository
import com.melvin.tvseries.series
import com.melvin.tvseries.seriesSuccess
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: SeriesRepository = mockk()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        coEvery { repository.searchSeries(any()) } returns seriesSuccess

        viewModel = SearchViewModel(repository)
    }

    @Test
    fun `validate correct search with success response`() {
        viewModel.onEvent(SearchEvent.OnTextChanged("Arrow"))

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(series, viewModel.state.series)
        assertEquals(false, viewModel.state.isLoading)
    }

    @Test
    fun `validate event NavigateToSeriesDetail`() {
        viewModel.onEvent(SearchEvent.NavigateToSeriesDetail(1))

        assertEquals(SearchUiEvent.NavigateToSeriesDetail(1), viewModel.state.uiEvent)
    }

    @Test
    fun `validate event OnBackClicked`() {
        viewModel.onEvent(SearchEvent.OnBackClicked)

        assertEquals(SearchUiEvent.NavigateBack, viewModel.state.uiEvent)
    }

    @Test
    fun `validate event UiEventHandled`() {
        viewModel.onEvent(SearchEvent.UiEventHandled)

        assertEquals(null, viewModel.state.uiEvent)
    }

}