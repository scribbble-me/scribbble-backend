package me.scribbble.backend.application

import me.scribbble.backend.domain.member.Member

data class MemberResponse(
    val id: String,
    val email: String,
    val username: String
) {
    constructor(member: Member) : this(
        member.id,
        member.email,
        member.username
    )
}

data class MemberRequest(
    val email: String,
    val password: String,
    val username: String
)
