package ru.itpark.authservice

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import ru.itpark.authservice.domain.user.User
import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import spock.lang.Specification

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthServiceTests extends Specification {

    @LocalServerPort
    int port

    KeycloakClient keycloakClient = new KeycloakClient()

    String adminAccessToken

    def setup() {

        println "Testing auth-service on port ${port}"

        adminAccessToken = keycloakClient.createUserToken(
                UserQuery.builder()
                        .username("oleguito")
                        .password("12345")
                        .build()
        )

        println "adminAccessToken: ${adminAccessToken}"
        println "is active: ${keycloakClient.validateToken(adminAccessToken)}"

        println "-----------------------------------------------------------"
    }

    def cleanup() {
        println "-----------------------------------------------------------"
    }

    def "context Loads"() {
        expect: 1
    }

    def "тестовый контроллер возвращает 'вы авторизованы'"() throws Exception {

        given:
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://localhost:${port}/test";

        when:
        String response = restTemplate.getForObject(url, String.class);

        then:
        println "response: ${response}"
        response.contains("вы авторизованы")
    }

    def "Получить список юзеров по ручке"() {

        given:
        def restTemplate = new RestTemplate()

        when:
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + adminAccessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        List<User> users = restTemplate.exchange(
                "https://localhost:${port}/api/users",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<User>>() {}
        ).getBody();

        println users

        then: 1
        users != null
        users.size() >= 0
    }


//    private void disableSslVerification() {
//        TrustManager[] trustAllCerts = [
//                new X509TrustManager() {
//                    X509Certificate[] getAcceptedIssuers() { null }
//                    void checkClientTrusted(X509Certificate[] certs, String authType) { }
//                    void checkServerTrusted(X509Certificate[] certs, String authType) { }
//                }
//        ]
//
//        SSLContext sc = SSLContext.getInstance("TLS")
//        sc.init(null, trustAllCerts, new SecureRandom())
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
//        HttpsURLConnection.setDefaultHostnameVerifier({ hostname, session -> true })
//    }
}

