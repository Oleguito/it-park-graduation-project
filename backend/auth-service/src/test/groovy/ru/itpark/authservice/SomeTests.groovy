package ru.itpark.authservice

import org.springframework.web.client.RestTemplate
import ru.itpark.keycloak.KeycloakClient;
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
        def keycloakClient = new KeycloakClient()
        def response = keycloakClient.getKeycloakResponse()
        println response['access_token']
        expect:
        1==1
    }
    
}
