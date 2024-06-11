package ru.itpark.authservice

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import spock.lang.Specification

class SomeTests extends Specification {

    def keycloakClient = new KeycloakClient()
    UserQuery userQuery

    def setup() {
        userQuery = new UserQuery()
        userQuery.setUsername("admin")
        userQuery.setPassword("12345")
    }

    def "tesskjhfds"() {
        expect:
        1 == 1
    }

    def "Use REST Template to get a jwt token from KeyCloak"() {
        setup: "KeycloakClient class"

        when:
        def response = keycloakClient.getUserInfo(userQuery)
        println response['access_token']

        then:
        response['access_token'] != null
    }

    def "UsersController handle to revoke Jwt token via keycloak"() {
        given:
        def adminToken = keycloakClient.createUserToken(userQuery)
        def toRevokeToken = adminToken

        when:
        def response = keycloakClient.revokeUserToken(adminToken, toRevokeToken)
        println response

        then:
        response
    }
}
