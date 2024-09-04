package com.example.kotlinservertest.service

interface LoginService {
    fun login(username: String, password: String): String?
}