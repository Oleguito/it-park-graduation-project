package ru.itpark.projectservice.infrastructure.config.certs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class CertsConfig {

    @Value("${spring.ssl.key-store-password}")
    private String certPassword;
    
    private final String certPasswordEnv = System.getenv("GP_PROJECTSERVICE_KEYSTORE_PASSWORD");

    public void configureCerts() {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            try (InputStream inputStream = new FileInputStream("certificate.p12")) {
                keyStore.load(inputStream, certPasswordEnv.toCharArray());
            }
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        try {
                            return new X509Certificate[] {
                                (X509Certificate) keyStore.getCertificate("my-cert")
                            };
                        } catch (KeyStoreException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    
                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    
                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };
            
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, javax.net.ssl.SSLSession session) {
                    return true;
                }
            });
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException |
                 KeyManagementException e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
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
                if(hostname.equals("localhost")) return true;
                return false;
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("PostConstruct certs config END");

    }

}
