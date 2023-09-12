package com.melvin.tvseries

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melvin.tvseries.home.presentation.list.HomeScreen
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeEvent
import com.melvin.tvseries.home.presentation.list.viewmodel.HomeViewModel
import com.melvin.tvseries.home.presentation.search.SearchScreen
import com.melvin.tvseries.home.presentation.search.viewmodel.SearchViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            val context = LocalContext.current
            val viewModel: HomeViewModel = hiltViewModel()

            with(viewModel.state) {
                LaunchedEffect(errorMessage) {
                    val errorMessage = errorMessage
                    if (errorMessage != null) {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        viewModel.onEvent(HomeEvent.ErrorShown)
                    }
                }

                LaunchedEffect(navigateToSearch) {
                    if (navigateToSearch == true) {
                        navController.navigate(Routes.SearchScreen.route)
                        viewModel.onEvent(HomeEvent.SearchNavigated)
                    }
                }
            }

            HomeScreen(viewModel.state, viewModel::onEvent)
        }

        composable(Routes.SearchScreen.route) {
            val viewModel: SearchViewModel = hiltViewModel()

            SearchScreen(state = viewModel.state, onEvent = viewModel::onEvent)
        }
    }
}