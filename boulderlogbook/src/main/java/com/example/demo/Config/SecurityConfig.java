package com.example.demo.Config;

import com.example.demo.Security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
.requestMatchers("/api/user-climbs/me").permitAll()

                // CORS preflight
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                // PUBLIC
                .requestMatchers(
                    "/api/auth/**",
                    "/api/users/register",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                // ✅ EXPLICIET: /me (CLIMBER + ADMIN)
                .requestMatchers(
                    "/api/user-climbs/me",
                    "/api/user-climbs/log"
                ).hasAnyRole("CLIMBER", "ADMIN")

                // ADMIN ONLY
                .requestMatchers(
                    "/api/climbs/{id}/deactivate",
                    "/api/locations/create",
                    "/api/climbs/create"
                ).hasRole("ADMIN")

                // OVERIGE SECURED ENDPOINTS
                .requestMatchers(
                    "/api/locations/**",
                    "/api/goals/**",
                    "/api/style-tags/**",
                    "/api/climbs/**",
                    "/api/users/**"
                ).hasAnyRole("CLIMBER", "ADMIN")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

