package ru.itpark.authservice.domain.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtDefinition {
    @JsonProperty("exp")
    private long exp;
    @JsonProperty("iat")
    private long iat;
    @JsonProperty("jti")
    private String jti;
    @JsonProperty("iss")
    private String iss;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("typ")
    private String typ;
    @JsonProperty("azp")
    private String azp;
    @JsonProperty("sid")
    private String sid;
    @JsonProperty("acr")
    private String acr;
    @JsonProperty("allowed-origins")
    private List<String> allowedOrigins;
    @JsonProperty("realm_access")
    private RealmAccess realmAccess;
    @JsonProperty("resource_access")
    private ResourceAccess resourceAccess;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    @JsonProperty("name")
    private String name;
    @JsonProperty("preferred_username")
    private String preferredUsername;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("email")
    private String email;

    public static class RealmAccess {
        @JsonProperty("roles")
        private List<String> roles;

        public List<String> getRoles() {
            return roles;
        }
    }

    public static class ResourceAccess {
        @JsonProperty("auth-service")
        private AuthService authService;

        public AuthService getAuthService() {
            return authService;
        }
    }

    public static class AuthService {
        @JsonProperty("roles")
        private List<String> roles;

        public List<String> getRoles() {
            return roles;
        }
    }
}
