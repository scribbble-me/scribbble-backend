package me.scribbble.backend.acceptance

import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.scribbble.backend.acceptance.support.AcceptanceTest
import me.scribbble.backend.application.MemberRequest
import org.junit.jupiter.api.Test

class HeartAcceptanceTest : AcceptanceTest() {

    @Test
    fun `회원에게 하트가 생성된다`() {
        // given
        val request = MemberRequest("devhudi@gmail.com", "password12345", "후디")
        val memberId = post("/api/members/", request).jsonPath().getString("id")

        // when
        val extractableResponse = post("/api/members/${memberId}/hearts")

        // then
        extractableResponse.jsonPath().getLong("id") shouldBeGreaterThan 0
        extractableResponse.jsonPath().getString("memberId") shouldBe memberId
        extractableResponse.jsonPath().getString("clientIp") shouldNotBe ""
        extractableResponse.jsonPath().getString("userAgent") shouldNotBe ""
    }
}
