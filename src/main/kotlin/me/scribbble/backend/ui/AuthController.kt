package me.scribbble.backend.ui

import me.scribbble.backend.application.AuthService
import me.scribbble.backend.application.LoginRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RequestMapping("/api/auth")
@RestController
class AuthController(
    private val authService: AuthService
) {

    @PostMapping
    fun login(
        @RequestBody loginRequest: LoginRequest,
        httpServletRequest: HttpServletRequest
    ) {
        val response = authService.login(loginRequest)

        val session = httpServletRequest.session
        session.setAttribute("id", response.id)
    }
}
