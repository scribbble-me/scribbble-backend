package me.scribbble.backend.application

data class LoginRequest(
    val email: String,
    val password: String
)
