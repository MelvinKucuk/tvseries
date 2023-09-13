package com.melvin.tvseries.home.domain

data class Series(
    val id: Int = 0,
    val name: String = "",
    val genres: List<String> = listOf(),
    val schedule: Schedule = Schedule(),
    val image: Image = Image(),
    val summary: String = "",
    val seasons: List<Season> = listOf()
)

data class Image(
    val medium: String = "",
    val original: String = ""
)

data class Schedule(
    val time: String = "",
    val days: List<String> = listOf()
)
