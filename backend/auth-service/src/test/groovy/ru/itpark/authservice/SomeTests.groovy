package ru.itpark.authservice

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient;
import spock.lang.Specification

class SomeTests extends Specification {

    def "tesskjhfds"() {
        expect:
        1 == 1
    }

    def "Use REST Template to get a jwt token from KeyCloak"() {
        given:
        def keycloakClient = new KeycloakClient()

        def query = new UserQuery()
        query.setUsername("admin")
        query.setPassword("12345")

        when:
        def response = keycloakClient.getUserInfo(query)
        println response['access_token']

        then:
        response['access_token'] != null
    }

    def "UsersController handle to revoke Jwt token via keycloak"() {
        given:
        def keycloakClient = new KeycloakClient()

        def query = new UserQuery()
        query.setUsername("admin")
        query.setPassword("12345")

        def adminToken = keycloakClient.createUserToken(query);
        def toRevokeToken = adminToken

        when:
        def response = keycloakClient.revokeUserToken(adminToken, toRevokeToken)

        then:
        response
    }
}
