package com.example.kotlinservertest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book", description = "Book API")
class BookController {

  @Operation(summary = "책 리스트")
  @GetMapping
  fun getBooks(): ResponseEntity<List<BookResponse>> {
    return ResponseEntity.ok(emptyList())
  }

  @Operation(summary = "책 검색")
  @GetMapping("/{id}")
  fun getBook(@PathVariable id: String): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse("", "", "", ""))
  }

  @Operation(summary = "책 정보 등록")
  @PostMapping
  fun createBook(@RequestBody request: CreateBookRequest): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse("", request.title, request.author, request.description))
  }

  @Operation(summary = "책 정보 업데이트")
  @PutMapping("/{id}")
  fun updateBook(
    @PathVariable id: String,
    @RequestBody request: UpdateBookRequest
  ): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse(id, request.title, request.author, request.description))
  }

  @Operation(summary = "책 정보 삭제")
  @DeleteMapping("/{id}")
  fun deleteBook(@PathVariable id: String): ResponseEntity<Unit> {
    return ResponseEntity.ok().build()
  }
}

data class BookResponse(
  val id: String,
  val title: String,
  val author: String,
  val description: String
)

data class CreateBookRequest(
  val title: String,
  val author: String,
  val description: String
)

data class UpdateBookRequest(
  val title: String,
  val author: String,
  val description: String
)