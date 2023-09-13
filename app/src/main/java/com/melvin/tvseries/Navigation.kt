package com.melvin.tvseries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.melvin.tvseries.core.util.makeToast
import com.melvin.tvseries.home.presentation.detail.series.SeriesDetailScreen
import com.melvin.tvseries.home.presentation.detail.series.viewmodel.SeriesDetailUiEvent
import com.melvin.tvseries.home.presentation.detail.series.viewmodel.SeriesDetailViewModel
import com.melvin.tvseries.home.presentation.list.HomeScreen
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeEvent
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeUiEvent
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeViewModel
import com.melvin.tvseries.home.presentation.search.SearchScreen
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchEvent
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchUiEvent
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    if (uiEvent != null) {
                        when (uiEvent) {
                            HomeUiEvent.NavigateToSearch ->
                                navController.navigate(Routes.SearchScreen.route)
                            is HomeUiEvent.NavigateToSeriesDetail ->
                                navController.navigate(
                                    Routes.SeriesDetailScreen.getDestination(uiEvent.seriesId)
                                )

                            is HomeUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        }
                        viewModel.onEvent(HomeEvent.UiEventHandled)
                    }
                }
            }

            HomeScreen(viewModel.state, viewModel::onEvent)
        }

        composable(Routes.SearchScreen.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    if (uiEvent != null) {
                        when (uiEvent) {
                            is SearchUiEvent.NavigateToSeriesDetail ->
                                navController.navigate(
                                    Routes.SeriesDetailScreen.getDestination(uiEvent.seriesId)
                                )

                            SearchUiEvent.NavigateBack -> navController.popBackStack()
                            is SearchUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        }
                        viewModel.onEvent(SearchEvent.UiEventHandled)
                    }
                }
            }

            SearchScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }

        composable(
            route = Routes.SeriesDetailScreen.getCompleteRoute(),
            arguments = listOf(
                navArgument(Routes.SeriesDetailScreen.SERIES_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: SeriesDetailViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    if (uiEvent != null) {
                        when (uiEvent) {
                            SeriesDetailUiEvent.NavigateBack -> navController.popBackStack()
                            is SeriesDetailUiEvent.ShowError -> makeToast(context, uiEvent.errorMessage)
                        }
                    }
                }
            }

            SeriesDetailScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }
    }
}