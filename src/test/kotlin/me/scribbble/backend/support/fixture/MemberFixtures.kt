package me.scribbble.backend.support.fixture

import me.scribbble.backend.application.MemberRequest
import me.scribbble.backend.domain.member.Member

const val EMAIL: String = "devhudi@gmail.com"
const val PASSWORD: String = "password12345"
const val USERNAME: String = "후디"

fun createMember(
    email: String = EMAIL,
    password: String = PASSWORD,
    username: String = USERNAME
) = Member(email, password, username)

fun createMemberRequest(
    email: String = EMAIL,
    password: String = PASSWORD,
    username: String = USERNAME
) = MemberRequest(email, password, username)
