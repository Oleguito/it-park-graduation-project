package ru.itpark.authservice.infrastructure.config.security.keycloak;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;

import java.util.Map;

@Component
public class KeycloakClient {

//    @Value("${keycloak.client-secret}")
    private final String clientSecret = System.getenv("client-secret");

    public boolean revokeUserToken(String adminToken, String toRevokeToken){
        final var restTemplate = new RestTemplate();

        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(adminToken);

        var body = new LinkedMultiValueMap <String, String>();
        body.add("token", toRevokeToken);
        body.add("token_type_hint", "refresh_token");
        body.add("client_id", "grad-project-oleguito");
        body.add("client_secret", clientSecret);

        final var requestEntity = new HttpEntity <MultiValueMap <String, String>>(body, headers);
        ResponseEntity <Map> responseEntity =
                restTemplate.postForEntity(
                        "https://auth.dppmai.ru/realms/group-1/protocol/openid-connect/revoke",
                        requestEntity,
                        Map.class);

        return responseEntity.getStatusCode()== HttpStatusCode.valueOf(200);
    }

    public Map getUserAuthInfo(UserQuery user) {

        final var restTemplate = new RestTemplate();
        
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        var body = new LinkedMultiValueMap <String, String>();
        body.add("grant_type", "password");
//        body.add("grant_type", "client_credentials");
        body.add("client_secret", clientSecret);
        body.add("client_id", "grad-project-oleguito");
        body.add("username", user.getUsername());
        body.add("password", user.getPassword());
        
        final var requestEntity = new HttpEntity <MultiValueMap <String, String>>(body, headers);
        ResponseEntity <Map> responseEntity =
                restTemplate.postForEntity(
                        "https://auth.dppmai.ru/realms/group-1/protocol/openid-connect/token",
                        requestEntity,
                        Map.class);
        
        return responseEntity.getBody();
    }

    public String createUserToken(UserQuery user) {

        return (String) getUserAuthInfo(user).get("access_token");
    }

    public boolean validateToken(String token) {

        final var restTemplate = new RestTemplate();

        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = new LinkedMultiValueMap <String, String>();
        body.add("client_secret", clientSecret);
        body.add("client_id", "grad-project-oleguito");
        body.add("token", token);

        final var requestEntity = new HttpEntity <MultiValueMap <String, String>>(body, headers);
        ResponseEntity<Map> responseEntity =
                restTemplate.postForEntity(
                        "https://auth.dppmai.ru/realms/group-1/protocol/openid-connect/token/introspect",
                        requestEntity,
                        Map.class);

        return (boolean) responseEntity.getBody().get("active");

    }
}
