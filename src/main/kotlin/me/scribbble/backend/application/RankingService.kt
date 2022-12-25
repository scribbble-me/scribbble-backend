package me.scribbble.backend.application

import me.scribbble.backend.domain.like.HeartRepository
import me.scribbble.backend.domain.member.Member
import me.scribbble.backend.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class RankingService(
    private val memberRepository: MemberRepository,
    private val heartRepository: HeartRepository
) {

    // TODO: 리팩토링 및 성능 개선
    fun getRanking(schoolId: Long): List<MemberPublicResponse> {
        val members = memberRepository.findBySchoolId(schoolId)

        val memberHeartCount = mutableMapOf<Member, Long>()
        for (member in members) {
            val heartCount = heartRepository.countByMemberId(member.id)
            memberHeartCount.put(member, heartCount)
        }

        return memberHeartCount.toList()
            .sortedByDescending { (_, value) -> value }
            .map { (key, _) -> key }
            .toList()
            .take(10)
            .map { member -> MemberPublicResponse(member) }
    }

    fun getMemberRanking(memberId: String): RankingResponse {
        val member = memberRepository.findByIdOrNull(memberId)

        require(member != null) { "회원이 존재하지 않습니다" }

        val members = memberRepository.findBySchoolId(member.school.id)

        val memberHeartCount = mutableMapOf<Member, Long>()
        for (member in members) {
            val heartCount = heartRepository.countByMemberId(member.id)
            memberHeartCount.put(member, heartCount)
        }

        val ranking = memberHeartCount.toList()
            .sortedByDescending { (_, value) -> value }
            .map { (key, _) -> key }
            .toList()
            .map { member -> member.id }
            .indexOf(memberId) + 1

        return RankingResponse(ranking, SchoolResponse(member.school))
    }
}
