package ru.itpark.authservice.infrastructure.config.security.filters;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itpark.authservice.application.user.facade.UserCommandFacade;
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final KeycloakClient keycloakClient;

    private final UserCommandFacade userCommandFacade;
    
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
