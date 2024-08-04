package com.example.kotlinservertest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinServerTestApplication

fun main(args: Array<String>) {
    println("testtest")
    runApplication<KotlinServerTestApplication>(*args)
}
