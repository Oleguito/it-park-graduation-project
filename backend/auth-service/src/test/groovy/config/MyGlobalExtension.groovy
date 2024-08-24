package config

import org.spockframework.runtime.extension.IGlobalExtension
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate

@Component
class MyGlobalExtension implements IGlobalExtension {

    String certPassword = System.getenv("GP_AUTHSERVICE_KEYSTORE_PASSWORD")

    @Override
    void start() {
        print "SSL Config BEGIN..."
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream("certificate.p12")) {
            keyStore.load(inputStream, certPassword.toCharArray());
        }
        TrustManager[] trustAllCerts = [
                new X509TrustManager() {
                    X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] certs = new X509Certificate[1];
                        certs[0] = keyStore.getCertificate("your-alias") as X509Certificate;
                        return certs;
                    }
                    void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        ]

        def sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts, new SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory())
        HttpsURLConnection.setDefaultHostnameVerifier({ hostname, session -> true })
        println "SSL Config END"
    }
}
