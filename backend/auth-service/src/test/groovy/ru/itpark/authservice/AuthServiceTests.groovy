package ru.itpark.authservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.web.client.RestClientSsl
import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.io.Resource
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestClient
import ru.itpark.authservice.presentation.web.users.UsersController
import spock.lang.Specification

import javax.net.ssl.*
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WithMockUser
@TestPropertySource(locations = "classpath:application.yml")
class AuthServiceTests extends Specification {

    @LocalServerPort
    int port

    @Autowired
    UsersController controller

    @Value('classpath:${server.ssl.key-store}')
    private Resource keyStore   // inject keystore specified in config

    @Value('${server.ssl.key-store-password}')
    private String keyStorePassword  // inject password from config

    @Autowired
    SslBundles sslBundles;

    @Autowired
    RestTemplateBuilder restTemplateBuilder

    @Autowired
    RestClient.Builder restClientBuilder

    @Autowired
    RestClientSsl restClientSsl

    RestClient restClient

    def setup() {
//        restClient = restClientBuilder
//        .baseUrl("https://localhost:${port}")
//        .apply(restClientSsl.fromBundle("keycloak"))
//        .build()


//        // Load the keystore
//        KeyStore ks = KeyStore.getInstance("PKCS12")
//        ks.load(keyStore.getInputStream(), keyStorePassword.toCharArray())
//
//        // Create SSL context
//        SSLContext sslContext = SSLContext.getInstance("TLS")
//        sslContext.init(null, null, new java.security.SecureRandom())
//
//        // Apply SSL context to RestClient
//        restClient = restClientBuilder
//                .baseUrl("https://localhost:${port}")
//                .apply(restClientSsl.fromBundle("keycloak"))
//                .build()

//        // Load the keystore
//        KeyStore ks = KeyStore.getInstance("PKCS12")
//        ks.load(keyStore.getInputStream(), keyStorePassword.toCharArray())
//
//        // Create TrustManagerFactory
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//        tmf.init(ks)
//
//        // Create SSL context
//        SSLContext sslContext = SSLContext.getInstance("TLS")
//        sslContext.init(null, tmf.getTrustManagers(), new java.security.SecureRandom())
//
//        // Apply SSL context to RestClient
//        restClient = restClientBuilder
//                .baseUrl("https://localhost:${port}")
//                .apply(restClientSsl.fromBundle("keycloak"))
//                .build()

//        // Load the keystore
//        KeyStore ks = KeyStore.getInstance("PKCS12")
//        ks.load(keyStore.getInputStream(), keyStorePassword.toCharArray())
//
//        // Create TrustManagerFactory
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//        tmf.init(ks)
//
//        // Create SSL context
//        SSLContext sslContext = SSLContext.getInstance("TLS")
//        sslContext.init(null, tmf.getTrustManagers(), new java.security.SecureRandom())

        def sslContext = sslBundles.getBundle("keycloak").createSslContext()

        // HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory())
        HttpsURLConnection.setDefaultHostnameVerifier({ hostname, session -> true })

        // Apply SSL context to RestClient
        restClient = restClientBuilder
                .baseUrl("https://localhost:${port}")
                .apply(restClientSsl.fromBundle("keycloak"))
                .build()

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

    def "User Controller loads"() {

        expect:
        controller != null
    }

}