package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.scribbble.backend.security.LoginFailedException
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.EMAIL
import me.scribbble.backend.support.fixture.PASSWORD
import me.scribbble.backend.support.fixture.USERNAME
import me.scribbble.backend.support.fixture.createMemberRequest

@ServiceTest
class AuthServiceTest(
    val authService: AuthService,
    val memberService: MemberService
) : StringSpec({


    "로그인에 성공한다" {
        // given
        val memberRequest = createMemberRequest()
        memberService.join(memberRequest)

        // when
        val loginRequest = LoginRequest(EMAIL, PASSWORD)
        val actual = authService.login(loginRequest)

        // then
        actual.email shouldBe EMAIL
        actual.username shouldBe USERNAME
    }

    "로그인에 실패한다" {
        // given
        val loginRequest = LoginRequest("wrong_email@gmail.com", "1234")

        // when & then
        shouldThrow<LoginFailedException> { authService.login(loginRequest) }
    }
})
