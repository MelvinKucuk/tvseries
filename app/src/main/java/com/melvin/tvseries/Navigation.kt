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
import com.melvin.tvseries.authentication.presentation.PinScreen
import com.melvin.tvseries.authentication.presentation.viewmodel.PinEvent
import com.melvin.tvseries.authentication.presentation.viewmodel.PinUiEvent
import com.melvin.tvseries.authentication.presentation.viewmodel.PinViewModel
import com.melvin.tvseries.core.util.makeToast
import com.melvin.tvseries.home.presentation.detail.episode.EpisodeDetailScreen
import com.melvin.tvseries.home.presentation.detail.episode.viewmodel.EpisodeDetailEvent
import com.melvin.tvseries.home.presentation.detail.episode.viewmodel.EpisodeDetailUiEvent
import com.melvin.tvseries.home.presentation.detail.episode.viewmodel.EpisodeDetailViewModel
import com.melvin.tvseries.home.presentation.detail.series.SeriesDetailScreen
import com.melvin.tvseries.home.presentation.detail.series.viewmodel.SeriesDetailEvent
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
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {

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
                            HomeUiEvent.NavigateToPin ->
                                navController.navigate(Routes.PinScreen.route)
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
                            is SeriesDetailUiEvent.ShowError -> makeToast(
                                context,
                                uiEvent.errorMessage
                            )

                            is SeriesDetailUiEvent.NavigateToEpisodeDetail -> {
                                navController.navigate(
                                    Routes.EpisodeDetailScreen.getDestination(
                                        seriesId = uiEvent.seriesId,
                                        seasonNumber = uiEvent.seasonNumber,
                                        episodeNumber = uiEvent.episodeNumber
                                    )
                                )
                            }
                        }
                    }
                    viewModel.onEvent(SeriesDetailEvent.OnUiEventHandled)
                }
            }

            SeriesDetailScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }

        composable(
            route = Routes.EpisodeDetailScreen.getCompleteRoute(),
            arguments = listOf(
                navArgument(Routes.EpisodeDetailScreen.SERIES_ID) {
                    type = NavType.IntType
                },
                navArgument(Routes.EpisodeDetailScreen.EPISODE_NUMBER) {
                    type = NavType.IntType
                },
                navArgument(Routes.EpisodeDetailScreen.SEASON_NUMBER) {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: EpisodeDetailViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    if (uiEvent != null) {
                        when (uiEvent) {
                            EpisodeDetailUiEvent.NavigateBack -> navController.popBackStack()
                            is EpisodeDetailUiEvent.ShowError ->
                                makeToast(context, uiEvent.errorMessage)
                        }
                        viewModel.onEvent(EpisodeDetailEvent.OnBackClicked)
                    }
                }
            }

            EpisodeDetailScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }

        composable(Routes.PinScreen.route) {
            val viewModel: PinViewModel = hiltViewModel()
            val context = LocalContext.current

            with(viewModel.state) {
                LaunchedEffect(uiEvent) {
                    if (uiEvent != null) {
                        when (uiEvent) {
                            PinUiEvent.NavigateBack -> navController.popBackStack()
                            PinUiEvent.NavigateHome ->
                                navController.navigate(Routes.HomeScreen.route) {
                                    popUpTo(Routes.PinScreen.route) {
                                        inclusive = true
                                    }
                                }

                            PinUiEvent.ShowInvalidPin -> makeToast(context, "Invalid pin")
                        }
                        viewModel.onEvent(PinEvent.OnUiEventHandled)
                    }
                }
            }

            PinScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }
    }
}