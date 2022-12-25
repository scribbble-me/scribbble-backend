package me.scribbble.backend.support.fixture

import me.scribbble.backend.domain.school.School

const val SCHOOL_NAME = "XX 학교"

fun createSchool(name: String = SCHOOL_NAME) = School(name)
