package com.example.kotlinservertest.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {


    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(components)
        .info(apiInfo())

    private fun apiInfo() = Info()
        .title("Kotlin Test API")
        .description("Kotlin Test API")
        .version("1.0.0")




    var components: Components = Components()



}