package com.example.kotlinservertest.controller


import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@Tag(name = "UserController")
@RestController
@RequestMapping("/api/v1/user")
class TestController {
    private val logger = LoggerFactory.getLogger(TestController::class.java)
    @Operation(summary = "정보 조회", description = "정보 조회")
    @GetMapping
    fun getInfo(): String {
        return "내정보"
    }
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    fun postLogin(@RequestBody body: Any): String {
        logger.info("Returning response: $body")
        return "Hello, World!"
    }
    @Operation(summary = "정보 수정", description = "정보 수정")
    @PutMapping
    fun putInfo(): String {

        return "Hello, World!"
    }
    @Operation(summary = "정보 삭제", description = "정보 삭제")
    @DeleteMapping
    fun deleteInfo(): String {
        return "Hello, World!"
    }
}