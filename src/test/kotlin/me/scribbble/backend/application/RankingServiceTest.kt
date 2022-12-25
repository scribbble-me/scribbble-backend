package me.scribbble.backend.application

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import me.scribbble.backend.domain.like.Heart
import me.scribbble.backend.domain.like.HeartRepository
import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.member.MemberRepository
import me.scribbble.backend.domain.school.School
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.annotation.ServiceTest
import me.scribbble.backend.support.fixture.createMember

@ServiceTest
class RankingServiceTest(
    private val rankingService: RankingService,
    private val schoolRepository: SchoolRepository,
    private val memberRepository: MemberRepository,
    private val heartRepository: HeartRepository
) : StringSpec({

    "학교 ID를 사용하여, 회원 랭킹을 가져온다" {
        // given
        val school = schoolRepository.save(School("XX 학교"))

        // 멤버 생성
        val members: List<Member> = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K").map { username ->
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

        // when
        val ranking: List<MemberPublicResponse> = rankingService.getRanking(school.id)
        val actual = ranking.map { member -> member.username }

        // then
        actual shouldContainExactly listOf("K", "J", "I", "H", "G", "F", "E", "D", "C", "B")
    }
})
