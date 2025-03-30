package com.example.kotlinservertest.controller

import com.example.kotlinservertest.dto.*
import com.example.kotlinservertest.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "인증 관련 API")
class AuthController(private val authService: AuthService) {

    @Operation(
        summary = "로그인",
        description = "사용자 로그인 및 토큰 발급",
        responses = [
            ApiResponse(responseCode = "200", description = "로그인 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패")
        ]
    )
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ResponseForm<LoginResponse>> {


        return ResponseEntity.ok(authService.login(loginRequest))
    }

    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @GetMapping("/refresh")
    fun getRefresh(): String {
        return ""
    }

    @Operation(
        summary = "회원가입", description = "회원가입", responses = [
            ApiResponse(responseCode = "200", description = "회원가입 성공"),
            ApiResponse(responseCode = "400", description = "회원가입 실패"),
            ApiResponse(responseCode = "401", description = "회원가입 실패")
        ]
    )
    @PostMapping("/join")
    fun join(@RequestBody joinRequest: JoinRequest): ResponseEntity<ResponseForm<JoinResponse>> {
        print("--- ${joinRequest.email}")


        return ResponseEntity.ok(authService.join(joinRequest))
    }

}