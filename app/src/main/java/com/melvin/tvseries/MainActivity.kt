package com.melvin.tvseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.melvin.tvseries.core.presentaiton.viewmodel.MainViewModel
import com.melvin.tvseries.ui.theme.TvseriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {
            TvseriesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHost = rememberNavController()
                    val startDestination = if (viewModel.state.shouldNavigateToPin == true) {
                        Routes.PinScreen.route
                    } else {
                        Routes.HomeScreen.route
                    }
                    Navigation(
                        navController = navHost,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}