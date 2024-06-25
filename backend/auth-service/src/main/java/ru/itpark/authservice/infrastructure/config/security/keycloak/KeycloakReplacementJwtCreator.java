package ru.itpark.authservice.infrastructure.config.security.keycloak;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Map;

@Component
public class KeycloakReplacementJwtCreator {

    private final Algorithm algorithm;

    public KeycloakReplacementJwtCreator() throws JOSEException {
        RSAKeyGenerator gen = new RSAKeyGenerator(2048);

        final var pair = gen.generate();

        final var rsaPublicKey = pair.toRSAPublicKey();
        final var rsaPrivateKey = pair.toRSAPrivateKey();

        this.algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);;
    }

    public String create() throws NoSuchAlgorithmException, JOSEException {

        Map headerMap = new LinkedMap<String, String>();
        headerMap.put("alg", "RS256");
        headerMap.put("typ", "JWT");
        // headerMap.put("kid", "NEAhFChiwAepP__pewDG1Xr02pWmaXH6Y0OVFGFIrbE");

        String jwt = JWT.create()
                .withHeader(headerMap)
                .withClaim("string-claim", "string-value")
                .withClaim("preferred_username", "admin")
                .withClaim("number-claim", 42)
                .withClaim("bool-claim", true)
                .withClaim("exp",
                        Instant.now()
                                .plusSeconds(3600*24*365))
                .withClaim("iat", Instant.now())
                .withClaim("active", true)
                .sign(algorithm);
        return jwt;
    }

    public void verify(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        jwtVerifier.verify(jwt);
    }
}
