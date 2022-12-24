package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.EMAIL
import me.scribbble.backend.support.fixture.USERNAME
import me.scribbble.backend.support.fixture.createMemberRequest

@ServiceTest
class MemberServiceTest(
    val memberService: MemberService
) : StringSpec({

    "사용자가 가입에 성공한다" {
        // given
        val memberRequest = createMemberRequest()

        // when
        val memberResponse: MemberResponse = memberService.join(memberRequest)

        // then
        memberResponse.id shouldHaveLength 36
        memberResponse.email shouldBe EMAIL
        memberResponse.username shouldBe USERNAME
    }

    "이미 가입한 이메일로 가입 시도시, 예외가 발생한다" {
        // given
        val memberRequest = createMemberRequest()
        memberService.join(memberRequest)

        // when & then
        shouldThrow<java.lang.IllegalArgumentException> {
            memberService.join(memberRequest)
        }
    }
})
