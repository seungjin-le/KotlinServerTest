package com.example.kotlinservertest.repository

import com.example.kotlinservertest.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {

    fun findByEmail(email: String): List<User>
}