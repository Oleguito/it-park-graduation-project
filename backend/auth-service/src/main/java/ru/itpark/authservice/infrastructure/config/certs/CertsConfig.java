package ru.itpark.authservice.infrastructure.config.certs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class CertsConfig {

//    @Value("${spring.ssl.key-store-password}")
//    private String certPassword;
    
    private final String certPasswordEnv = System.getenv("GP_AUTHSERVICE_KEYSTORE_PASSWORD");
   
    
//    @PostConstruct
    public void configurateCerts() {
        
        System.out.println("PostConstruct certs config BEGIN");

        KeyStore keyStore = null;
        try (InputStream inputStream = new FileInputStream("certificate.p12")) {
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, certPasswordEnv.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, certPasswordEnv.toCharArray());

            // Инициализация TrustManagerFactory с вашим хранилищем
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);

            // Настройка SSLContext для использования вашего сертификата
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

            // Установка SSLContext по умолчанию для HttpsURLConnection
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> {
                System.out.println("hostname: " + hostname);
                return hostname.contains("localhost");
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("PostConstruct certs config END");

    }

}
