package com.hussain.securewebcrawler.config;

import com.hussain.securewebcrawler.service.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {

    @Bean
    AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    OncePerRequestFilter jwtRequestFilter(final JwtUserDetailsService jwtUserDetailsService,
                                          final JwtTokenUtil jwtTokenUtil) {
        return new JwtRequestFilter(jwtUserDetailsService, jwtTokenUtil);
    }

    @Bean
    JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
