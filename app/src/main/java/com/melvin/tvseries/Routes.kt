package com.melvin.tvseries

sealed class Routes(val route: String) {
    object HomeScreen: Routes("home_screen")
    object SearchScreen: Routes("search_screen")
    object SeriesDetailScreen : Routes("series_detail_screen") {
        fun getCompleteRoute() = "$route?$SERIES_ID={$SERIES_ID}"
        fun getDestination(seriesId: Int) = "$route?$SERIES_ID=$seriesId"


        const val SERIES_ID = "series_id"
    }
}