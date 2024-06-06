package ru.itpark.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

class KeycloakClient {
    
    public Map getKeycloakResponse() {
        
        final var restTemplate = new RestTemplate();
        
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        var body = new LinkedMultiValueMap <String, String>();
        body.add("grant_type", "password");
        body.add("client_id", "grad-project-oleguito");
        body.add("username", "admin");
        body.add("password", "12345");
        
        final var requestEntity = new HttpEntity <MultiValueMap <String, String>>(body, headers);
        ResponseEntity <Map> responseEntity =
                restTemplate.postForEntity(
                        "https://auth.dppmai.ru/realms/group-1/protocol/openid-connect/token",
                        requestEntity,
                        Map.class);
        
        return responseEntity.getBody();
    }
}
