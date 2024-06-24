package ru.itpark.authservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import ru.itpark.authservice.domain.user.User
import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import ru.itpark.authservice.presentation.web.users.UsersController
import spock.lang.Specification
import spock.lang.Subject

import javax.net.ssl.HttpsURLConnection
import java.sql.ResultSet

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WithMockUser
// @TestPropertySource(locations = "classpath:application.yml")
@Testcontainers
class AuthServiceTests extends Specification {

    @Subject
    @Autowired
    UsersController controller

    @LocalServerPort
    int port

    @Autowired
    RestClient.Builder restClientBuilder

    static PostgreSQLContainer<?> postgresContainer
            = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:14.2"))
            .withDatabaseName("authservicedb")
            .withUsername("authservice")
            .withPassword("12345")


    RestClient restClient

    @Autowired
    KeycloakClient keycloakClient

    String adminAccessToken

    static {
        postgresContainer.start()
    }

    def setup() {

        // Без этого SSL не будет работать
        HttpsURLConnection.setDefaultHostnameVerifier(
                { hostname, session -> true})

        restClient = restClientBuilder
                .baseUrl("https://localhost:${port}")
                .build()

        adminAccessToken = keycloakClient.createUserToken(
                new UserQuery("admin", "12345"))
    }

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

    def "postgres testcontainer is not null"() {
        expect:
        postgresContainer != null
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