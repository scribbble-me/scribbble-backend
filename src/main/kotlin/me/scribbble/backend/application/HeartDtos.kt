package me.scribbble.backend.application

import me.scribbble.backend.domain.like.Heart

data class HeartResponse(
    val id: Long,
    val memberId: String,
    val clientIp: String,
    val userAgent: String
) {
    constructor(heart: Heart) : this(
        heart.id,
        heart.member.id,
        heart.clientIp,
        heart.userAgent
    )
}

data class HeartRequest(
    val memberId: String,
    val clientIp: String,
    val userAgent: String
)
