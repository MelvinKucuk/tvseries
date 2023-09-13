package com.melvin.tvseries

sealed class Routes(val route: String) {
    object HomeScreen: Routes("home_screen")
    object SearchScreen: Routes("search_screen")
    object SeriesDetailScreen : Routes("series_detail_screen") {
        fun getCompleteRoute() = "$route?$SERIES_ID={$SERIES_ID}"
        fun getDestination(seriesId: Int) = "$route?$SERIES_ID=$seriesId"


        const val SERIES_ID = "series_id"
    }
    object EpisodeDetailScreen : Routes("episode_detail_screen") {
        fun getCompleteRoute() = "$route?$SERIES_ID={$SERIES_ID}&$EPISODE_NUMBER={$EPISODE_NUMBER}" +
                "&$SEASON_NUMBER={$SEASON_NUMBER}"
        fun getDestination(
            seriesId: Int,
            seasonNumber: Int,
            episodeNumber: Int
        ) = "$route?$SERIES_ID=$seriesId&$EPISODE_NUMBER=$episodeNumber&$SEASON_NUMBER=$seasonNumber"

        const val SERIES_ID = "series_id"
        const val SEASON_NUMBER = "season_number"
        const val EPISODE_NUMBER = "episode_number"
    }
}