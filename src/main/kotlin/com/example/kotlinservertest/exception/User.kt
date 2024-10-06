package com.example.kotlinservertest.exception


// Email 없음
class UserNotFoundException : RuntimeException("User not found.")

// 비밀번호 틀림
class InvalidPasswordException : RuntimeException("Invalid password.")