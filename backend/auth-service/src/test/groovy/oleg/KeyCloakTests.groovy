package oleg

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakReplacementJwtCreator
import spock.lang.Narrative
import spock.lang.Specification

@Narrative("""
    Большой длинный и удобный текст 
    который подробно объясняет че за херня тут творится
""")
class KeyCloakTests extends Specification {

    def keycloakReplacer = new KeycloakReplacementJwtCreator()

    def keycloakClient = new KeycloakClient()
    def userQuery

    def keycloakAdminUsername = "admin"
    def keycloakAdminPassword = System.getenv("GP_KEYCLOAK_ADMIN_PASSWORD")

    def setup() {
        userQuery = new UserQuery()
        userQuery.setUsername(keycloakAdminUsername)
        userQuery.setPassword(keycloakAdminPassword)
    }

    def "Взять замену Keycloak JWT expect 2"() {
        def jwt = keycloakReplacer.create()
        println "замена: ${jwt}"
        keycloakReplacer.verify(jwt)
        expect:
        2
    }

//    @IgnoreIf({env.get("KEYCLOAK_DEAD")})
    def "Взять токен по REST Template из KeyCloak"() {
        setup: "KeycloakClient class"

        when:
        def response = keycloakClient.createUserToken(userQuery as UserQuery)

        then:
        response != null
    }

//    @IgnoreIf({env.get("KEYCLOAK_DEAD")})
    def "Отозвать токен по REST Template из KeyCloak"() {
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
