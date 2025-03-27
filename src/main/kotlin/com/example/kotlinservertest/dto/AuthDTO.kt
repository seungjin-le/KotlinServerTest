package com.example.kotlinservertest.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
    @Schema(description = "사용자 이메일 또는 ID", example = "user@example.com")
    val email: String,
    @Schema(description = "사용자 비밀번호", example = "password123")
    val password: String
)

data class LoginResponse(
    @Schema(description = "JWT 토큰", example = "")
    val accessToken: String,
    @Schema(description = "리프레시 토큰", example = "")
    val refreshToken: String
)

data class JoinRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com")
    val email: String,
    @Schema(description = "사용자 비밀번호", example = "password123")
    val password: String
)

data class JoinResponse(
    @Schema(description = "사용자 이메일", example = "user@example.com")
    val email: String,
    @Schema(description = "사용자 비밀번호", example = "password123")
    val password: String
)