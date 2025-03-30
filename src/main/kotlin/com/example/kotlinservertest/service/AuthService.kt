package com.example.kotlinservertest.service

import com.example.kotlinservertest.dto.*
import com.example.kotlinservertest.entity.User
import com.example.kotlinservertest.repository.UserRepository
import com.example.kotlinservertest.utils.JwtUtil
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {


    @Operation(summary = "로그인", description = "사용자 로그인 및 토큰 발급")
    fun login(loginRequest: LoginRequest): ResponseForm<LoginResponse> {

        val user = userRepository.findByEmail(loginRequest.email).firstOrNull()
        val pwCheck = passwordEncoder.matches(loginRequest.password, user?.password.toString())

        if (user === null) {
            return ResponseForm(
                status = 404,
                message = "로그인 실패",
                data = null
            )
        }


        if (!pwCheck) {
            return ResponseForm(
                status = 404,
                message = "비밀번호가 일치하지 않습니다",
                data = null
            )
        }

        // 토큰 생성
        val accessToken = jwtUtil.createAccessToken(user.id!!)
        val refreshToken = jwtUtil.createRefreshToken(user.id)

        return ResponseForm(
            status = 200,
            message = "로그인 성공",
            data = LoginResponse(
                accessToken = accessToken,
                refreshToken = refreshToken,

                )
        )
    }


    @Operation(summary = "회원가입", description = "회원가입")
    fun join(joinRequest: JoinRequest): ResponseForm<JoinResponse> {

        if (userRepository.findByEmail(joinRequest.email).isNotEmpty()) {
            return ResponseForm(
                status = 404,
                message = "회원가입 실패",
                data = null
            )
        }


        val user = User(
            email = joinRequest.email,
            password = passwordEncoder.encode(joinRequest.password)
        )


        userRepository.save(user)
        return ResponseForm(
            status = 200,
            message = "회원가입 성공",
            data = null
        )

    }
}