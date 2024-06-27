package oleg

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.test.context.SpringBootTest
import ru.itpark.authservice.AuthServiceApplication
import spock.lang.Specification

import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.PKIXParameters
import java.security.cert.TrustAnchor

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = AuthServiceApplication
)
class CertificatesTests extends Specification {

    @Autowired
    SslBundles sslBundles

    def whenLoadingCacertsKeyStore_thenCertificatesArePresent() {
        KeyStore keyStore = sslBundles.getBundle("keycloak").getStores().getKeyStore()
        PKIXParameters params = new PKIXParameters(keyStore)

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .toList()
    }

    private KeyStore loadKeyStore() {
        String relativeCacertsPath = "/lib/security/cacerts".replace("/", File.separator);
        String filename = System.getProperty("java.home") + relativeCacertsPath;
        FileInputStream is = new FileInputStream(filename);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        String password = "changeit";
        keystore.load(is, password.toCharArray());

        return keystore;
    }
}