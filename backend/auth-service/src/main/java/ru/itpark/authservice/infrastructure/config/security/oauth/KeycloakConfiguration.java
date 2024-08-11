package ru.itpark.authservice.infrastructure.config.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ru.itpark.authservice.infrastructure.config.security.filters.JwtTokenValidatorFilter;
import ru.itpark.authservice.infrastructure.config.security.jwt.JwtAuthConverter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class KeycloakConfiguration {

    private final JwtAuthConverter jwtAuthConverter;
    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        var configuration = new CorsConfiguration();
//        configuration.applyPermitDefaultValues();
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Указываем конкретный источник для доступа
////        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // Явно указываем разрешенные заголовки
////        configuration.setAllowCredentials(true); // Разрешаем куки с других доменов
//
//        var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        System.out.println("is returned");
//        var filter =  new CorsFilter(corsConfigurationSource());
//        System.out.println(filter);
//        return filter;
//    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.addFilterBefore(corsFilter(), SessionManagementFilter.class).csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authz -> {
//
//        });

        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .anyRequest().permitAll()
                );

        http.oauth2ResourceServer((oauth2) -> oauth2
                .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthConverter))
        )
                .addFilterAfter(jwtTokenValidatorFilter, BearerTokenAuthenticationFilter.class);


        http.sessionManagement(configurer -> configurer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }
}
