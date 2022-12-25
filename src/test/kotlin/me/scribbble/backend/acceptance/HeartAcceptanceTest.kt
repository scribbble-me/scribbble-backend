package me.scribbble.backend.acceptance

import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.scribbble.backend.acceptance.support.AbstractAcceptanceTest
import me.scribbble.backend.application.MemberRequest
import org.junit.jupiter.api.Test

class HeartAcceptanceTest : AbstractAcceptanceTest() {

    @Test
    fun `회원에게 하트가 생성된다`() {
        // given
        val request = MemberRequest("devhudi@gmail.com", "password12345", "후디", 1L)
        val memberId = post("/api/members/", request).jsonPath().getString("id")

        // when
        val extractableResponse = post("/api/members/${memberId}/hearts")

        // then
        extractableResponse.jsonPath().getLong("id") shouldBeGreaterThan 0
        extractableResponse.jsonPath().getString("memberId") shouldBe memberId
        extractableResponse.jsonPath().getString("clientIp") shouldNotBe ""
        extractableResponse.jsonPath().getString("userAgent") shouldNotBe ""
    }

    @Test
    fun `회원의 하트 개수를 조회한다`() {
        // given
        val request = MemberRequest("devhudi@gmail.com", "password12345", "후디", 1L)
        val memberId = post("/api/members/", request).jsonPath().getString("id")

        for (ignored in 1..10) {
            post("/api/members/${memberId}/hearts")
        }

        // when
        val response = get("/api/members/${memberId}/hearts")

        // then
        response.jsonPath().getLong("count") shouldBe 10
    }
}
