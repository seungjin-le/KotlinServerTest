package com.example.kotlinservertest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "TestController")
@RestController
//@RequestMapping("/api/v1/test")
class TestController {

    @Operation(summary = "Get Test API", description = "This is GET API")
    @RequestMapping("/hello")
    fun helloWorld(): String {
        return "Hello, World!"
    }
}