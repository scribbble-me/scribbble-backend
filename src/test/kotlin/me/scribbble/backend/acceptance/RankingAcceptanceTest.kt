package me.scribbble.backend.acceptance

import io.kotest.matchers.shouldBe
import me.scribbble.backend.acceptance.support.AbstractAcceptanceTest
import me.scribbble.backend.domain.like.Heart
import me.scribbble.backend.domain.like.HeartRepository
import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.member.MemberRepository
import me.scribbble.backend.domain.school.School
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.fixture.createMember
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class RankingAcceptanceTest : AbstractAcceptanceTest() {

    @Autowired
    lateinit var schoolRepository: SchoolRepository

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var heartRepository: HeartRepository

    lateinit var school: School
    lateinit var members: List<Member>

    @BeforeEach
    fun setUp() {
        school = schoolRepository.save(School("XX 학교"))

        // 멤버 생성
        members = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K").map { username ->
            memberRepository.save(
                createMember(
                    email = "${username}@gmail.com",
                    username = username,
                    school = school
                )
            )
        }

        for (i in 0 until members.size) {
            val member = members.get(i)

            for (j in 0..i) {
                heartRepository.save(Heart(member, "", ""))
            }
        }
    }

    // TODO: 테스트 케이스 보강
    @Test
    fun `특정 학교의 랭킹을 조회한다`() {
        // given & when
        val response = get("/api/ranking/schools/${school.id}")

        // then
        response.statusCode() shouldBe 200
    }

    @Test
    fun `특정 유저의 학교 랭킹을 조회한다`() {
        // given & when
        val response = get("/api/ranking/members/${members[0].id}")

        // then
        response.jsonPath().getLong("ranking") shouldBe 11
        response.jsonPath().getString("school.name") shouldBe "XX 학교"
    }

    // TOOD: 자기 자신 랭크 조회 테스트 케이스 추가
}
