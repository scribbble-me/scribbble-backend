package me.scribbble.backend.acceptance

import io.kotest.matchers.shouldBe
import me.scribbble.backend.acceptance.support.AcceptanceTest
import me.scribbble.backend.application.MemberRequest
import org.junit.jupiter.api.Test

class MemberAcceptanceTest : AcceptanceTest() {

    @Test
    fun `회원의 회원가입이 정상 진행된다`() {
        // given
        val request = MemberRequest("devhudi@gmail.com", "password12345", "후디")

        // when
        val extractableResponse = post("/api/members/", request)

        // then
        extractableResponse.body().jsonPath().getString("id").length shouldBe 36
        extractableResponse.body().jsonPath().getString("email") shouldBe "devhudi@gmail.com"
        extractableResponse.body().jsonPath().getString("username") shouldBe "후디"
    }
}
