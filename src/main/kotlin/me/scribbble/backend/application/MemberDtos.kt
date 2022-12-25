package me.scribbble.backend.application

import me.scribbble.backend.domain.member.Member

data class MemberResponse(
    val id: String,
    val email: String,
    val username: String,
    val schoolId: Long
) {
    constructor(member: Member) : this(
        member.id,
        member.email,
        member.username,
        member.school.id
    )
}

data class MemberRequest(
    val email: String,
    val password: String,
    val username: String,
    val schoolId: Long
)
