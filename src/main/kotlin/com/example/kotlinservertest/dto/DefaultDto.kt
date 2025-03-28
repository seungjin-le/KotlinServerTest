package com.example.kotlinservertest.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ResponseForm<T>(
    @Schema(description = "상태 코드", example = "200")
    val status: Number,
    @Schema(description = "상태 메시지", example = "OK")
    val message: String,
    @Schema(description = "응답 데이터")
    val data: T? = null
)