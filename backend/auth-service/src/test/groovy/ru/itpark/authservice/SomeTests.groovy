package ru.itpark.authservice

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import spock.lang.Narrative
import spock.lang.Specification

@Narrative("""
    Большой длинный и удобный текст 
    который подробно объясняет че за херня тут творится
""")
class SomeTests extends Specification {

    def keycloakClient = new KeycloakClient()
    def userQuery

    def setup() {
        userQuery = new UserQuery()
        userQuery.setUsername("admin")
        userQuery.setPassword("12345")
    }

    def "Что-то да проверяем.. не?"() {
        expect:
        1 == 1
    }

    def "Use REST Template to get a jwt token from KeyCloak"() {
        setup: "KeycloakClient class"

        when:
        def response = keycloakClient.getUserAuthInfo(userQuery as UserQuery)
        println response['access_token']

        then:
        response['access_token'] != null
    }


    def "UsersController handle to revoke Jwt token via keycloak status 200"() {
        given:
        def adminToken = keycloakClient.createUserToken(userQuery as UserQuery)
        def toRevokeToken = adminToken

        when:
        def response = keycloakClient.revokeUserToken(adminToken, toRevokeToken)
        println "revoke user token returned 200: ${response}"

        then: "response should be status 200 for success"
        response
    }
}
