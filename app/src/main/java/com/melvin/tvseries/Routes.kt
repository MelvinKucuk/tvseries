package com.melvin.tvseries

sealed class Routes(val route: String) {
    object HomeScreen: Routes("home_screen")
}