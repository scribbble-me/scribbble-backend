package me.scribbble.backend.support.fixture

import me.scribbble.backend.application.MemberRequest
import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.school.School

const val EMAIL: String = "devhudi@gmail.com"
const val PASSWORD: String = "password12345"
const val USERNAME: String = "후디"

fun createMember(
    email: String = EMAIL,
    password: String = PASSWORD,
    username: String = USERNAME,
    school: School
) = Member(email, password, username, school)

fun createMemberRequest(
    email: String = EMAIL,
    password: String = PASSWORD,
    username: String = USERNAME,
    schoolId: Long
) = MemberRequest(email, password, username, schoolId)
