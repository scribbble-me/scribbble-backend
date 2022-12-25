package me.scribbble.backend.application

data class RankingResponse(
    val ranking: Int,
    val school: SchoolResponse
)
