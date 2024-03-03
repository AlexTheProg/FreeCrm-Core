package com.axc.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@Slf4j
public class WebSecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) //this looks for a bean named corsConfigurationSource
                .securityMatcher("/api/**")
                .oauth2ResourceServer(resourceServer ->
                    resourceServer.jwt(jwtConfigurer())
                )
                .securityContext(Customizer.withDefaults())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/actuator/health").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKeySpec("FKjaLc/68+xpTLI0fHY79Ib5i5ZH+5B2focQO3v5h+Dp9tXBHZlozi8RMPui71WL9t6vJX3453uWnlUTzKdJ3g==".getBytes(), "HS256");
        JwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey)
                .build();

        return token -> {
            log.info("token: {}", token);
            var jwt = jwtDecoder.decode(token);
            log.info("decoded: {}", jwt.toString());
            return jwt;
        };
    }

    private Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer> jwtConfigurer() {
        return jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder());
    }
}
