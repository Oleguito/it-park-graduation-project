package ru.itpark.authservice.infrastructure.config.security.keycloak

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import spock.lang.Specification

class KeycloakClientTests extends Specification {

    private keyCloakTests = new KeycloakClient()

    def "можем взять access_token и refresh_token"() {

        when:
        def response = keyCloakTests.getUserAuthInfo(
                UserQuery.builder()
                        .username("oleguito")
                        .password("12345")
                        .build()
        )

        then:
        println response
        response["access_token"] != null
        response["refresh_token"] != null
    }
}
