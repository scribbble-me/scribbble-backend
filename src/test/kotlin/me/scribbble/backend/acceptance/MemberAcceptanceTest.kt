package me.scribbble.backend.acceptance

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.scribbble.backend.acceptance.support.AbstractAcceptanceTest
import me.scribbble.backend.application.MemberRequest
import me.scribbble.backend.support.fixture.EMAIL
import me.scribbble.backend.support.fixture.PASSWORD
import me.scribbble.backend.support.fixture.USERNAME
import me.scribbble.backend.support.fixture.createLoginRequest
import org.junit.jupiter.api.Test

class MemberAcceptanceTest : AbstractAcceptanceTest() {

    @Test
    fun `회원의 회원가입이 정상 진행된다`() {
        // given
        val request = MemberRequest(EMAIL, PASSWORD, USERNAME, 1L)

        // when
        val extractableResponse = post("/api/members/", request)

        // then
        extractableResponse.body().jsonPath().getString("id").length shouldBe 36
        extractableResponse.body().jsonPath().getString("email") shouldBe EMAIL
        extractableResponse.body().jsonPath().getString("username") shouldBe USERNAME
    }

    @Test
    fun `회원을 단건 조회한다`() {
        // given
        val request = MemberRequest(EMAIL, PASSWORD, USERNAME, 1L)
        val memberResponse = post("/api/members/", request)
        val memberId = memberResponse.jsonPath().getString("id")

        // when
        val response = get("/api/members/${memberId}")

        // then
        response.jsonPath().getString("id") shouldBe memberId
        response.jsonPath().getString("username") shouldBe USERNAME
        response.jsonPath().getLong("school.id") shouldBe 1L
    }

    @Test
    fun `자기자신의 정보를 조회한다`() {
        // given
        val memberRequest = MemberRequest(EMAIL, PASSWORD, USERNAME, 1L)

        post("/api/members/", memberRequest)
        val cookie = post("/api/auth", createLoginRequest()).cookie("JSESSIONID") // TODO: 세션 유지 방법 리팩토링

        // when
        val response = get("/api/members/me", cookie)

        // then
        response.jsonPath().getString("id") shouldNotBe ""
        response.jsonPath().getString("username") shouldBe USERNAME
    }
}
