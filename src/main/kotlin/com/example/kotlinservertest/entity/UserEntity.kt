package com.example.kotlinservertest.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
  @Id
  val id: String? = null,
  val email: String,
  val password: String

)