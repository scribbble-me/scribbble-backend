package me.scribbble.backend.application

import me.scribbble.backend.domain.member.MemberRepository
import me.scribbble.backend.security.LoginFailedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val memberRepository: MemberRepository
) {

    fun login(request: LoginRequest): MemberResponse {
        val foundMember = memberRepository.findByEmail(request.email)

        if (foundMember == null || !foundMember.isCorrectPassword(request.password)) {
            throw LoginFailedException()
        }

        return MemberResponse(foundMember.id, foundMember.email, foundMember.username)
    }
}
