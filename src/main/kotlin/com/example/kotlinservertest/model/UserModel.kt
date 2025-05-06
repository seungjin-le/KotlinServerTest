package com.example.kotlinservertest.model


import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

class UserDomain(
  val id: String? = null,
  val email: String,
  private var password: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  var lastLoginAt: LocalDateTime? = null,
  private var loginAttempts: Int = 0,
  private var locked: Boolean = false
) {
  // 비밀번호 검증 로직을 도메인에 내장
  fun verifyPassword(rawPassword: String, encoder: PasswordEncoder): Boolean {
    if (locked) {
      throw AuthenticationException("계정이 잠겨 있습니다.")
    }

    val matches = encoder.matches(rawPassword, password)

    if (!matches) {
      loginAttempts++

      // 로그인 시도가 5회 이상 실패하면 계정 잠금
      if (loginAttempts >= 5) {
        locked = true
        throw AuthenticationException("로그인 시도 횟수 초과로 계정이 잠겼습니다.")
      }

      return false
    }

    // 로그인 성공시 카운터 초기화
    loginAttempts = 0
    lastLoginAt = LocalDateTime.now()
    return true
  }

  // 비밀번호 변경 로직
  fun changePassword(currentPassword: String, newPassword: String, encoder: PasswordEncoder): Boolean {
    if (encoder.matches(currentPassword, password)) {
      password = encoder.encode(newPassword)
      return true
    }
    return false
  }

  // 계정 잠금 해제
  fun unlock() {
    locked = false
    loginAttempts = 0
  }

  // 이메일 검증 (간단한 예시)
  fun isValidEmail(): Boolean {
    return email.contains("@") && email.contains(".")
  }

  // 엔티티로 변환하는 메소드
  fun toEntity(): com.example.kotlinservertest.entity.User {
    return com.example.kotlinservertest.entity.User(
      id = id,
      email = email,
      password = password
    )
  }

  companion object {
    // 엔티티로부터 도메인 객체 생성
    fun fromEntity(entity: com.example.kotlinservertest.entity.User): UserDomain {
      return UserDomain(
        id = entity.id,
        email = entity.email,
        password = entity.password
      )
    }
  }
}
