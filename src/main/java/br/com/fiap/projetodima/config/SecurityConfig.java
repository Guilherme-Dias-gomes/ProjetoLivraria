package br.com.fiap.projetodima.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/livros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/livros/{name}").permitAll()
        );
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter  .class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}
