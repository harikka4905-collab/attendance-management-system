package com.am.attendancemanagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC HTML PAGES
                        // =========================
                        .requestMatchers(
                                "/",
                                "/error",
                                "/login",
                                "/register",

                                "/admin-dashboard",

                                "/faculty-dashboard",
                                "/faculty-reports",

                                "/student-dashboard",
                                "/student-attendance",
                                "/student-subjects",
                                "/student-reports",

                                "/css/**",
                                "/js/**",
                                "/images/**"
                        )
                        .permitAll()

                        // =========================
                        // PUBLIC APIs
                        // =========================
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/student-report/test",
                                "/api/faculty-report/test"
                        )
                        .permitAll()

                        // =========================
                        // PROTECTED REPORT APIs
                        // =========================
                        .requestMatchers(
                                "/api/student-report/my-report",
                                "/api/faculty-report/all"
                        )
                        .authenticated()

                        // =========================
                        // ALL OTHER REQUESTS
                        // =========================
                        .anyRequest()
                        .authenticated()
                )

                // Disable Spring default login page
                .formLogin(form ->
                        form.disable()
                )

                // Disable HTTP Basic login
                .httpBasic(basic ->
                        basic.disable()
                )

                // JWT project handles logout
                .logout(logout ->
                        logout.disable()
                )

                // Add JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration
                .getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}