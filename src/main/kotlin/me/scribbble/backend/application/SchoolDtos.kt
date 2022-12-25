package me.scribbble.backend.application

import me.scribbble.backend.domain.school.School

data class SchoolResponse(
    val id: Long,
    val name: String
) {
    constructor(school: School) : this(school.id, school.name)
}
