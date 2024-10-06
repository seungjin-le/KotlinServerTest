package com.example.kotlinservertest.service

import com.example.kotlinservertest.dto.LoginRequest
import com.example.kotlinservertest.dto.TokenDto
import com.example.kotlinservertest.exception.InvalidPasswordException
import com.example.kotlinservertest.exception.UserNotFoundException
import com.example.kotlinservertest.utils.JwtUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtil: JwtUtil
) {
    // 간단한 유저 저장소 (실제 서비스에서는 DB 또는 다른 저장소 사용)
    private val users = mutableMapOf(
        "test@example.com" to BCryptPasswordEncoder().encode("password")
    )

    fun login(loginRequest: LoginRequest): TokenDto {
        val userPassword = users[loginRequest.email] ?: throw UserNotFoundException()

        if (!BCryptPasswordEncoder().matches(loginRequest.password, userPassword)) {
            throw InvalidPasswordException()
        }

        val accessToken = jwtUtil.createAccessToken(loginRequest.email)
        val refreshToken = jwtUtil.createRefreshToken(loginRequest.email)

        return TokenDto(accessToken, refreshToken)
    }
}