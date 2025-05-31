package com.example.kotlinservertest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book", description = "Book API")
class BookController {

  @Operation(summary = "Get all books")
  @GetMapping
  fun getBooks(): ResponseEntity<List<BookResponse>> {
    return ResponseEntity.ok(emptyList())
  }

  @Operation(summary = "Get a book by ID")
  @GetMapping("/{id}")
  fun getBook(@PathVariable id: String): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse("", "", "", ""))
  }

  @Operation(summary = "Create a new book")
  @PostMapping
  fun createBook(@RequestBody request: CreateBookRequest): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse("", request.title, request.author, request.description))
  }

  @Operation(summary = "Update a book")
  @PutMapping("/{id}")
  fun updateBook(
    @PathVariable id: String,
    @RequestBody request: UpdateBookRequest
  ): ResponseEntity<BookResponse> {
    return ResponseEntity.ok(BookResponse(id, request.title, request.author, request.description))
  }

  @Operation(summary = "Delete a book")
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