package me.scribbble.backend.domain.school

import org.springframework.data.jpa.repository.JpaRepository

interface SchoolRepository : JpaRepository<School, Long> {
    fun findTop5ByNameContaining(query: String): List<School>
}
