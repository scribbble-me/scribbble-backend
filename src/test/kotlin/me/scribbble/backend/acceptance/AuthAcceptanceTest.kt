package me.scribbble.backend.acceptance

import io.kotest.matchers.shouldNotBe
import me.scribbble.backend.acceptance.support.AbstractAcceptanceTest
import me.scribbble.backend.application.MemberRequest
import me.scribbble.backend.support.fixture.EMAIL
import me.scribbble.backend.support.fixture.PASSWORD
import me.scribbble.backend.support.fixture.USERNAME
import me.scribbble.backend.support.fixture.createLoginRequest
import org.junit.jupiter.api.Test

class AuthAcceptanceTest : AbstractAcceptanceTest() {

    @Test
    fun `로그인이 정상 진행된다`() {
        // given
        val memberRequest = MemberRequest(EMAIL, PASSWORD, USERNAME, 1L)
        post("/api/members/", memberRequest)

        // when
        val response = post("/api/auth", createLoginRequest())

        // then
        response.header("Set-Cookie") shouldNotBe null
    }
}
