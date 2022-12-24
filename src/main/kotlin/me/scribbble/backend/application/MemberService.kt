package me.scribbble.backend.application

import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    // TODO: 비밀번호 암호화

    @Transactional
    fun join(request: MemberRequest): MemberResponse {
        val member = Member(request.email, request.password, request.username)

        require(!memberRepository.existsByEmail(request.email)) { "이미 가입된 이메일입니다." }

        val savedMember = memberRepository.save(member)
        return MemberResponse(savedMember)
    }
}
