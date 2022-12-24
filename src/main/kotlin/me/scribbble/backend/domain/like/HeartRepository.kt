package me.scribbble.backend.domain.like

import org.springframework.data.jpa.repository.JpaRepository

interface HeartRepository : JpaRepository<Heart, Long> {
    fun countByMemberId(memberId: String): Long
}
