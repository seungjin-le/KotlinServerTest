package com.example.kotlinservertest.controller


@Tag(name = "RefreshController")
@RestController
@RequestMapping("/api/v1/refresh")
class AuthController {
    private val logger = LoggerFactory.getLogger(TestController::class.java)
    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @GetMapping
    fun getRefresh(): String {
        return ""
    }
}