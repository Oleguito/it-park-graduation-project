package ru.itpark.authservice.infrastructure.config.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final KeycloakClient keycloakClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        boolean isActive = false;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            System.out.println(token);
            isActive = keycloakClient.validateToken(token);
            System.out.println("is active: " +isActive);

            if (!isActive) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        filterChain.doFilter(request, response);

    }
}
