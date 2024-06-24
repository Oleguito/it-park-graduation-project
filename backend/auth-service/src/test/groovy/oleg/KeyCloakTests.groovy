package oleg

import org.hibernate.cfg.Environment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient
import spock.lang.Narrative
import spock.lang.Specification

@Narrative("""
    Большой длинный и удобный текст 
    который подробно объясняет че за херня тут творится
""")
class KeyCloakTests extends Specification {

    def keycloakClient = new KeycloakClient()
    def userQuery

    def setup() {
        userQuery = new UserQuery()
        userQuery.setUsername("admin")
        userQuery.setPassword("12345")
    }

    def "Взять токен по REST Template из KeyCloak"() {
        setup: "KeycloakClient class"

        when:
        def response = keycloakClient.createUserToken(userQuery as UserQuery)

        then:
        response != null
    }


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
