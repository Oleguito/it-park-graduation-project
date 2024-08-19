package ru.itpark.projectservice.infrastructure.config.certs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;

@Configuration
public class CertsConfig {

    @Value("${spring.ssl.key-store-password}")
    private String certPassword;

    @PostConstruct
    public void configurateCerts() {

        KeyStore keyStore = null;
        try (InputStream inputStream = new FileInputStream("src/main/resources/certificate.p12")) {
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, certPassword.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, certPassword.toCharArray());

            // Инициализация TrustManagerFactory с вашим хранилищем
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);

            // Настройка SSLContext для использования вашего сертификата
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

            // Установка SSLContext по умолчанию для HttpsURLConnection
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
