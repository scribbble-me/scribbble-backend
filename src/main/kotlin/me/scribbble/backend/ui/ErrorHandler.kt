package me.scribbble.backend.ui

import me.scribbble.backend.security.LoginFailedException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {

    // TODO: 에러 메시지 개선

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(e?.message ?: "")
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(e: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse("잘못된 HTTP Body 입니다.")
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler(LoginFailedException::class)
    fun handleForbidden(e: LoginFailedException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(e?.message ?: "")
        return ResponseEntity.status(401).body(response)
    }
}
