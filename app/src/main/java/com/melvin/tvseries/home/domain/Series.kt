package com.melvin.tvseries.home.domain

data class Series(
    var id: Int? = null,
    var name: String? = null,
    var genres: List<String?> = listOf(),
    var schedule: Schedule? = Schedule(),
    var image: Image? = Image(),
    var summary: String? = null,
)

data class Image(
    var medium: String? = null,
    var original: String? = null
)

data class Schedule(
    var time: String? = null,
    var days: List<String?> = listOf()
)
