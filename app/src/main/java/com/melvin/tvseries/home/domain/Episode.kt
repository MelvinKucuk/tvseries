package com.melvin.tvseries.home.domain

data class Episode(
    val name: String = "",
    val summary: String = "",
    val image: String = "",
    val seasonNumber: Int,
    val episodeNumber: Int
)
