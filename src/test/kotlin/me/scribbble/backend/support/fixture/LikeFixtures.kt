package me.scribbble.backend.support.fixture

import me.scribbble.backend.application.HeartRequest

const val CLIENT_IP: String = "127.0.0.1"
const val USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"

fun createLikeRequest(
    memberId: String,
    clientIp: String = CLIENT_IP,
    userAgent: String = USER_AGENT
) = HeartRequest(memberId, clientIp, userAgent)
