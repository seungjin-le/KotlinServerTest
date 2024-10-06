package com.example.kotlinservertest.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)