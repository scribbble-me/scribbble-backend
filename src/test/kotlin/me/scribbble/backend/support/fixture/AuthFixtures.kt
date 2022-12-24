package me.scribbble.backend.support.fixture

import me.scribbble.backend.application.LoginRequest

fun createLoginRequest(
    email: String = EMAIL,
    password: String = PASSWORD
) = LoginRequest(email, password)
