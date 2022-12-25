package me.scribbble.backend.ui

import me.scribbble.backend.application.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest

@RequestMapping("/api/members")
@RestController
class MemberController(
    private val memberService: MemberService,
    private val heartService: HeartService
) {

    @PostMapping
    fun join(@RequestBody request: MemberRequest): ResponseEntity<MemberResponse> {
        val response = memberService.join(request)
        return ResponseEntity.created(URI.create("/api/members/${response.id}")).body(response)
    }

    @GetMapping("/{memberId}")
    fun getUser(@PathVariable memberId: String): ResponseEntity<MemberPublicResponse> {
        val response = memberService.getMember(memberId)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{memberId}/hearts")
    fun createHeart(@PathVariable memberId: String, servletRequest: HttpServletRequest): ResponseEntity<HeartResponse> {
        val clientIp = servletRequest.getHeader("X-FORWARDED-FOR") ?: servletRequest.remoteAddr
        val userAgent = servletRequest.getHeader("USER-AGENT")

        val request = HeartRequest(memberId, clientIp, userAgent)
        val response = heartService.create(request)
        return ResponseEntity.created(URI.create("/api/${memberId}/hearts")).body(response)
    }

    @GetMapping("/{memberId}/hearts")
    fun getHeartCount(@PathVariable memberId: String): ResponseEntity<HeartCountResponse> {
        val response = heartService.getHeartCountOfMember(memberId)
        return ResponseEntity.ok(response)
    }
}
