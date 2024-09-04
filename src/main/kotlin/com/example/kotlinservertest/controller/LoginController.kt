package com.example.kotlinservertest.controller

import com.example.kotlinservertest.model.LoginRequest
import com.example.kotlinservertest.model.LoginResponse
import com.example.kotlinservertest.service.LoginService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/login")
class LoginController(private val loginService: LoginService) {

    @PostMapping
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = loginService.login(request.username, request.password)

        return if (token != null) {
            ResponseEntity(LoginResponse(token), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }
}