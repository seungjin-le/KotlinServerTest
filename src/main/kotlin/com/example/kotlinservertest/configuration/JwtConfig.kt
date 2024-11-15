package com.example.kotlinservertest.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
class JwtConfig(private val jwtTokenProvider: JwtTokenProvider) {
    @Bean
    fun filterChain(): SecurityFilterChain {
        return
            http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/login").permitAll()
                .antMatchers("/api/v1/refresh").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }
}