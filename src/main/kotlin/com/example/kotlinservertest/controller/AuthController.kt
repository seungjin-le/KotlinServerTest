package com.example.kotlinservertest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "RefreshController")
@RestController
@RequestMapping("/api/v1/refresh")
class AuthController {

    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @GetMapping
    fun getRefresh(): String {
        return ""
    }
}