package me.scribbble.backend.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.EMAIL
import me.scribbble.backend.support.fixture.USERNAME
import me.scribbble.backend.support.fixture.createMemberRequest
import me.scribbble.backend.support.fixture.createSchool

@ServiceTest
class MemberServiceTest(
    val memberService: MemberService,
    val schoolRepository: SchoolRepository
) : StringSpec({

    "사용자가 가입에 성공한다" {
        // given
        val schoolId: Long = schoolRepository.save(createSchool()).id
        val memberRequest = createMemberRequest(schoolId = schoolId)

        // when
        val memberResponse: MemberResponse = memberService.join(memberRequest)

        // then
        memberResponse.id shouldHaveLength 36
        memberResponse.email shouldBe EMAIL
        memberResponse.username shouldBe USERNAME
    }

    "이미 가입한 이메일로 가입 시도시, 예외가 발생한다" {
        // given
        val schoolId: Long = schoolRepository.save(createSchool()).id
        val memberRequest = createMemberRequest(schoolId = schoolId)
        memberService.join(memberRequest)

        // when & then
        shouldThrow<java.lang.IllegalArgumentException> {
            memberService.join(memberRequest)
        }
    }

    "유저를 단건 조회한다" {
        // given
        val schoolId: Long = schoolRepository.save(createSchool()).id
        val memberRequest = createMemberRequest(schoolId = schoolId)
        val memberResponse = memberService.join(memberRequest)

        // when
        val actual = memberService.getMember(memberResponse.id)

        // then
        actual.id shouldBe memberResponse.id
        actual.username shouldBe memberResponse.username
        actual.school.id shouldBe schoolId
    }

    "없는 유저를 단건 조회하면, 예외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            memberService.getMember("abc")
        }
    }
})
