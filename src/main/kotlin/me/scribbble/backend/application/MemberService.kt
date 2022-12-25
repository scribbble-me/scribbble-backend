package me.scribbble.backend.application

import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.member.MemberRepository
import me.scribbble.backend.domain.school.SchoolRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val schoolRepository: SchoolRepository
) {

    // TODO: 비밀번호 암호화

    @Transactional
    fun join(request: MemberRequest): MemberResponse {
        val school = schoolRepository.findByIdOrNull(request.schoolId)
        require(school != null) { "학교가 존재하지 않습니다." }

        val member = Member(request.email, request.password, request.username, school)

        require(!memberRepository.existsByEmail(request.email)) { "이미 가입된 이메일입니다." }

        val savedMember = memberRepository.save(member)
        return MemberResponse(savedMember)
    }

    fun getMember(memberId: String): MemberPublicResponse {
        val member = memberRepository.findByIdOrNull(memberId)
        require(member != null) { "회원을 찾을 수 없습니다." }

        return MemberPublicResponse(
            member.id,
            member.username,
            SchoolResponse(member.school)
        )
    }
}
