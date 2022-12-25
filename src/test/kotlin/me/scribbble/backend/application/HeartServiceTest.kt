package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.createLikeRequest
import me.scribbble.backend.support.fixture.createMemberRequest
import me.scribbble.backend.support.fixture.createSchool

@ServiceTest
class HeartServiceTest(
    private val heartService: HeartService,
    private val memberService: MemberService,
    private val schoolRepository: SchoolRepository
) : StringSpec({

    "좋아요할 대상 회원이 존재할 때 회원에 대해 좋아요가 생성된다" {
        // given
        val schoolId: Long = schoolRepository.save(createSchool()).id
        val savedMember = memberService.join(createMemberRequest(schoolId = schoolId))

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

    "특정 유저의 하트 개수를 가져온다 " {
        // given
        val schoolId: Long = schoolRepository.save(createSchool()).id
        val savedMember = memberService.join(createMemberRequest(schoolId = schoolId))

        val likeRequest = createLikeRequest(savedMember.id)
        for (ignored in 1..10) {
            heartService.create(likeRequest)
        }

        // when
        val actual = heartService.getHeartCountOfMember(savedMember.id)

        // then
        actual.memberId shouldBe savedMember.id
        actual.count shouldBe 10
    }
})
