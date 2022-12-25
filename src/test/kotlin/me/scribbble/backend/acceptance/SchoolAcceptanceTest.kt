package me.scribbble.backend.acceptance

import io.kotest.matchers.shouldBe
import me.scribbble.backend.acceptance.support.AbstractAcceptanceTest
import org.junit.jupiter.api.Test

class SchoolAcceptanceTest : AbstractAcceptanceTest() {

    @Test
    fun `학교를 검색한다`() {
        // given & when
        val response = get("/api/schools?query=고등학교")

        // then
        response.jsonPath().getString("[0].name") shouldBe "DD 고등학교"
        response.jsonPath().getString("[1].name") shouldBe "EE 고등학교"
    }
}
