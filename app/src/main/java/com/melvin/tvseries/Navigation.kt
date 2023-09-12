package com.melvin.tvseries

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melvin.tvseries.home.presentation.HomeScreen
import com.melvin.tvseries.home.presentation.viewmodel.HomeEvent
import com.melvin.tvseries.home.presentation.viewmodel.HomeViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            val context = LocalContext.current
            val viewModel: HomeViewModel = hiltViewModel()

            LaunchedEffect(viewModel.state.errorMessage) {
                val errorMessage = viewModel.state.errorMessage
                if (errorMessage != null) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(HomeEvent.ErrorShown)
                }
            }

            HomeScreen(viewModel.state, viewModel::onEvent)
        }
    }
}