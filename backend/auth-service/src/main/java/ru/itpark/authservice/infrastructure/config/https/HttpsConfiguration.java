package ru.itpark.authservice.infrastructure.config.https;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;


@Configuration
public class HttpsConfiguration {
    
//    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
//    new X509TrustManager() {
//        public X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//
//        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//        }
//
//        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//        }
//    }
//    };
//
//    @Bean
//    public RestTemplate build() {
//        try {
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, UNQUESTIONING_TRUST_MANAGER, new java.security.SecureRandom());
//
//            final var httpClientBuilder = HttpClientBuilder.create();
//            httpClientBuilder.setHostnameVerifier((X509HostnameVerifier) new NoopHostnameVerifier());
//            httpClientBuilder.setSslcontext(sslContext);
//            final var httpClient = httpClientBuilder.build();
//
//            final var requestFactory = new HttpComponentsClientHttpRequestFactory();
//            requestFactory.setHttpClient((HttpClient) httpClient);
//            return new RestTemplate(requestFactory);
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            throw new RuntimeException("Failed to create unsafe RestTemplate", e);
//        }
//    }
}
