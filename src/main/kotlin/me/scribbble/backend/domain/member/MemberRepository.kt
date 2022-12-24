package me.scribbble.backend.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {
    fun existsByEmail(email: String): Boolean
}
