package me.scribbble.backend.acceptance

import me.scribbble.backend.acceptance.support.AcceptanceTest
import me.scribbble.backend.application.MemberRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemberAcceptanceTest : AcceptanceTest() {

    @Test
    fun `회원의 회원가입이 정상 진행된다`() {
        // given
        val request = MemberRequest("devhudi@gmail.com", "password12345", "후디")

        // when
        val extractableResponse = post("/api/members/", request)

        // then
        assertThat(extractableResponse.body().jsonPath().getString("id").length).isEqualTo(36)
        assertThat(extractableResponse.body().jsonPath().getString("email")).isEqualTo("devhudi@gmail.com")
        assertThat(extractableResponse.body().jsonPath().getString("username")).isEqualTo("후디")
    }
}
