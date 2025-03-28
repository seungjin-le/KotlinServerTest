package com.example.kotlinservertest.service

import com.example.kotlinservertest.dto.JoinRequest
import com.example.kotlinservertest.dto.JoinResponse
import com.example.kotlinservertest.dto.LoginRequest
import com.example.kotlinservertest.dto.LoginResponse
import com.example.kotlinservertest.entity.User
import com.example.kotlinservertest.repository.UserRepository
import com.example.kotlinservertest.utils.JwtUtil
import io.swagger.v3.oas.annotations.Operation
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
) {


    @Operation(summary = "로그인", description = "사용자 로그인 및 토큰 발급")
    fun login(loginRequest: LoginRequest): LoginResponse {
        // 사용자 인증 로직
        val user = userRepository.findByEmail(loginRequest.email).firstOrNull()

        println("--- $user")
        // ?: throw RuntimeException("사용자를 찾을 수 없습니다")
        // 비밀번호 검증 (실제로는 암호화된 비밀번호 비교 필요)
        // 예: passwordEncoder.matches(loginRequest.password, user.password)
        if (user === null) {
            throw RuntimeException("사용자를 찾을 수 없습니다")
            return LoginResponse(
                accessToken = "",
                refreshToken = "",
            )
        }
        if (user.password != loginRequest.password) {
            throw RuntimeException("비밀번호가 일치하지 않습니다")
        }

        // 토큰 생성
        val accessToken = jwtUtil.createAccessToken(user.id!!)
        val refreshToken = jwtUtil.createRefreshToken(user.id)

        return LoginResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,

            )
    }


    @Operation(summary = "회원가입", description = "회원가입")
    fun join(joinRequest: JoinRequest): JoinResponse {
        print("--- ${joinRequest.email}")
        if (userRepository.findByEmail(joinRequest.email).isNotEmpty()) {
            throw RuntimeException("이메일 중복")

        }


        val user = User(
            email = joinRequest.email,
            password = joinRequest.password
        )
        userRepository.save(user)

        return JoinResponse(
            email = joinRequest.email,
            password = joinRequest.password
        )
    }
}