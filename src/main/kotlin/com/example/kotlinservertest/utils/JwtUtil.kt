package com.example.kotlinservertest.utils

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.access.expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshTokenExpiration: Long
) {

    fun createAccessToken(userId: String): String {
        return createToken(userId, accessTokenExpiration)
    }

    fun createRefreshToken(userId: String): String {
        return createToken(userId, refreshTokenExpiration)
    }

    private fun createToken(userId: String, expiration: Long): String {
        val claims = Jwts.claims().setSubject(userId)
        val now = Date()
        val expirationDate = Date(now.time + expiration * 1000)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }
}