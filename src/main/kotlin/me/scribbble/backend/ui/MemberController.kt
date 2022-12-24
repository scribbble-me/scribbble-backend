package me.scribbble.backend.ui

import me.scribbble.backend.application.MemberRequest
import me.scribbble.backend.application.MemberResponse
import me.scribbble.backend.application.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/members")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping
    fun join(@RequestBody request: MemberRequest): ResponseEntity<MemberResponse> {
        val response = memberService.join(request)
        return ResponseEntity.ok(response)
    }
}
