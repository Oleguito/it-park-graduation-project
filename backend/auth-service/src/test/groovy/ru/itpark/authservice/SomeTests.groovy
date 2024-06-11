package ru.itpark.authservice

import ru.itpark.authservice.domain.user.dto.queries.UserQuery
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient;
import spock.lang.Specification

class SomeTests extends Specification {

    def "tesskjhfds"() {
        expect:
        1 == 1
    }

//    def "skjdfhksjdhf"() {
////        String folderPath = "/usr/src/mymaven/backend";
////        System.out.println(folderPath);
//        runLsInFolder("/usr/src/")
//        runLsInFolder("/usr/src/mymaven")
//        runLsInFolder("/usr/src/mymaven/backend")
//        expect:
//        1 == 1
//    }

//    def runLsInFolder(folder) {
//        def process = "ls ${folder}".execute()
//        def output = new StringBuffer()
//        process.consumeProcessOutput(output, System.err)
//        process.waitFor()
//        println "Output: $output"
//    }

    def "Use REST Template to get a jwt token from KeyCloak"() {
        given:
        def keycloakClient = new KeycloakClient()

        def query = new UserQuery()
        query.setUsername("admin")
        query.setPassword("12345")

        when:
        def response = keycloakClient.getKeycloakResponse(query)
        println response['access_token']

        then:
        response['access_token'] != null
    }
    
}
