package me.scribbble.backend.security

class LoginFailedException(override val message: String? = "로그인에 실패하였습니다.") : RuntimeException()
