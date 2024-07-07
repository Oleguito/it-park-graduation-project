package ru.itpark.authservice

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpHost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestClient
import org.testcontainers.spock.Testcontainers
import ru.itpark.authservice.domain.user.User
import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import ru.itpark.authservice.presentation.web.users.UsersController
import spock.lang.Specification

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.SecureRandom
import java.security.cert.X509Certificate

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WithMockUser
// @TestPropertySource(locations = "classpath:application.yaml")
//@Testcontainers
class AuthServiceTests extends Specification {

    @Autowired
    UsersController controller

    @LocalServerPort
    int port

    @Autowired
    RestClient.Builder restClientBuilder

    RestClient restClient

    @Autowired
    KeycloakClient keycloakClient

    String adminAccessToken


    def setup() {

        restClient = restClientBuilder
                .baseUrl("https://localhost:${port}")
                .build()

        adminAccessToken = keycloakClient.createUserToken(
                new UserQuery("oleguito", "12345"))

        println "-----------------------------------------------------------"
    }

    def cleanup() {
        println "-----------------------------------------------------------"
    }

    // @IgnoreIf({env.get("KEYCLOAK_DEAD")})
    def "Получить список юзеров по ручке"() {
        given:
        def users
                = restClient
                .get()
                .uri("/api/users")
                .header("Authorization", "Bearer ${adminAccessToken}")
                .retrieve()
                .body(List<User>)


        expect:
        users.size() >= 0
    }



    def "тестовый контроллер возвращает 'вы авторизованы'"() throws Exception {

        def response
                = restClient
                .get()
                .uri("/test")
                .retrieve()
                .body(String)

        println ""
        println response
        println ""

        expect:
        response.contains("вы авторизованы")
    }


    def "context Loads"() {
        expect:
        1
    }

    def "User Controller gets injected"() {

        expect:
        controller != null
    }

    private void disableSslVerification() {
        TrustManager[] trustAllCerts = [
                new X509TrustManager() {
                    X509Certificate[] getAcceptedIssuers() { null }
                    void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        ]

        SSLContext sc = SSLContext.getInstance("TLS")
        sc.init(null, trustAllCerts, new SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
        HttpsURLConnection.setDefaultHostnameVerifier({ hostname, session -> true })
    }
}

