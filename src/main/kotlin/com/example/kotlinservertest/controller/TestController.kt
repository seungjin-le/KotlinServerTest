package com.example.kotlinservertest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping

data class TestResponseDto(
    @field:Schema(description = "버전은 숫자로")
    val test: String,
    @field:Schema(description = "버전은 숫자로")
    val version: Int
)


data class GetRequestDto(
    @field:Schema(description = "문구 패턴 테스트", pattern = "swagger|openAPI|docs|fox")
    val test: String,
    @field:Schema(description = "버전은 숫자로")
    val version: Int,
    @field:Schema(description = "생략 가능한 변수")
    val notRequired: String?
)

@Tag(name="Test", description = "Test")
@Operation(summary = "Get Test API", description = "This is GET API")
@ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success", content = [
        Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = TestResponseDto::class)))
    ]),
    ApiResponse(responseCode = "202", description = "사용하지 않는 파라미터", content = [Content()])
])
@GetMapping("/get-test")
fun getTest(@Valid @ParameterObject getRequestDto: GetRequestDto, bindingResult: BindingResult): TestResponseDto {
    return TestResponseDto(getRequestDto.test, getRequestDto.version)
}