package ru.itpark.authservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
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

    static PostgreSQLContainer<?> pg
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
        pg.start()
    }

    def setup() {

        // Без этого SSL не будет работать
        HttpsURLConnection.setDefaultHostnameVerifier(
                { hostname, session -> true})

        restClient = restClientBuilder
                .baseUrl("https://localhost:${port}")
                .build()

        adminAccessToken = keycloakClient.createUserToken(
                new UserQuery("oleguito", "12345"))

//        adminAccessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHJpbmctY2xhaW0iOiJzdHJpbmctdmFsdWUiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbiIsIm51bWJlci1jbGFpbSI6NDIsImJvb2wtY2xhaW0iOnRydWUsImV4cCI6MTc1MDg0NTY2NywiaWF0IjoxNzE5MzA5NjY3LCJhY3RpdmUiOnRydWV9.LkxGbLYdIkBXbfKfjAfWcu6wAl3kpcNxojCuq-OFPwdO_K9clQFHwOMoC4CIyBT0zwV2iam9o1mG4a3GZ44d41hIXVqTNmAgkWC9fZVzEWYR0EORXcfr8qNpucHiqwourTAXhGOwp0SYOAHGZGbUb1-9_uBWQvEnFkmfBZW9hBSOS60Ah_vRL0WmmtSg3imr0ZXiIOfTle7TFw0rWHkBIXJvt2ang1NrinCHttTz930VIOc6MhLeejQnqZ9kkT_IEkgka4r8YAcmm4bHhWwhYYTFRu22HIhDmH7GzHdfApzAFv3ALg28aVflbN72ZTeYU8TyR-8xVl12pvmMraAwNQ"

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

    def "postgres testcontainer is not null"() {
        expect:
        pg != null
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