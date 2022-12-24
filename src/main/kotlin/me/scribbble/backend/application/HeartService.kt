package me.scribbble.backend.application

import me.scribbble.backend.domain.like.Heart
import me.scribbble.backend.domain.like.HeartRepository
import me.scribbble.backend.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class HeartService(
    private val heartRepository: HeartRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun create(heartRequest: HeartRequest): HeartResponse {
        val targetMember = memberRepository.findByIdOrNull(heartRequest.memberId)
        require(targetMember != null) { "좋아요 대상 회원이 존재하지 않습니다." }

        val heart = Heart(targetMember, heartRequest.clientIp, heartRequest.userAgent)
        val savedLike = heartRepository.save(heart)

        return HeartResponse(savedLike)
    }
}
