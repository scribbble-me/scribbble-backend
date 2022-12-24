package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.createLikeRequest
import me.scribbble.backend.support.fixture.createMemberRequest

@ServiceTest
class LikeServiceTest(
    private val heartService: HeartService,
    private val memberService: MemberService
) : StringSpec({

    "좋아요할 대상 회원이 존재할 때 회원에 대해 좋아요가 생성된다" {
        // given
        val savedMember = memberService.join(createMemberRequest())

        // when
        val likeRequest = createLikeRequest(savedMember.id)
        val actual: HeartResponse = heartService.create(likeRequest)

        // then
        actual.id shouldBeGreaterThan 0
    }

    "좋아요할 대상 회원이 존재하지 않을 때, 좋아요를 생성하면 예외가 발생한다" {
        // given
        val invalidMemberId = "!!!@@@###"

        // when
        val likeRequest = createLikeRequest(invalidMemberId)

        // then
        shouldThrow<IllegalArgumentException> {
            heartService.create(likeRequest)
        }
    }
})
