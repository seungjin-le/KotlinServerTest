package com.example.kotlinservertest.configuration


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@SecurityRequirement(name = "bearerAuth")
class SwaggerConfig {
    @Bean
    fun swaggerApi(): Docket = Docket(DocumentationType.OAS_30)
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .apiInfo(swaggerInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.colabear754.swagger_example.controllers"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)

    private fun swaggerInfo() = ApiInfoBuilder()
        .title("스웨거 테스트")
        .description("스웨거 API 테스트")
        .version("1.0.0")
        .build()

    private fun getConsumeContentTypes(): Set<String> {
        val consumes = HashSet<String>()
        consumes.add("multipart/form-data")
        return consumes
    }

    private fun getProduceContentTypes(): Set<String> {
        val produces = HashSet<String>()
        produces.add("application/json;charset=UTF-8")
        return produces
    }

}



