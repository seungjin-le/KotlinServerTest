package com.example.kotlinservertest.schema

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "LoginRequestDto")
data class LoginSchema(
    val email: String,
    val password: String
)