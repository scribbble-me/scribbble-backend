package me.scribbble.backend.ui

import me.scribbble.backend.application.MemberPublicResponse
import me.scribbble.backend.application.RankingResponse
import me.scribbble.backend.application.RankingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RequestMapping("/api/ranking")
@RestController
class RankingController(
    val rankingService: RankingService
) {

    @GetMapping("/schools/{schoolId}")
    fun getRanking(@PathVariable schoolId: Long): ResponseEntity<List<MemberPublicResponse>> {
        val response = rankingService.getRanking(schoolId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/members/{memberId}")
    fun getMemberRanking(@PathVariable memberId: String): ResponseEntity<RankingResponse> {
        val response = rankingService.getMemberRanking(memberId)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/my-ranking")
    fun getMyRanking(httpServletRequest: HttpServletRequest): ResponseEntity<RankingResponse> {
        val session = httpServletRequest.getSession()
        val memberId = session.getAttribute("id") as String

        val response = rankingService.getMemberRanking(memberId)
        return ResponseEntity.ok(response)
    }
}
