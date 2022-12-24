package me.scribbble.backend.support

import me.scribbble.backend.domain.like.HeartRepository
import me.scribbble.backend.domain.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DatabaseCleaner {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var heartRepository: HeartRepository

    @Transactional
    fun clear() {
        memberRepository.deleteAll()
        heartRepository.deleteAll()
    }
}
