//package ru.itpark.projectservice.presentation.projects
//
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.CoreMatchers.*;
//import org.springframework.aop.scope.ScopedProxyUtils
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.server.LocalServerPort
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Import
//import org.springframework.test.web.servlet.MockMvc
//import spock.lang.Specification
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.http.MediaType.*
//
//import ru.itpark.projectservice.application.service.project.ProjectService;
//import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class ProjectsControllerTests extends Specification {
//
//    @LocalServerPort
//    int port
//
//    @Autowired
//    MockMvc mvc
//
//    @Autowired
//    ProjectRepo projectRepo
//
//    def setup() {}
//
//    def cleanup() {}
//
//    def "context loads"() {
//        expect: 1
//    }
//
//    def "test controller 200"() {
//        when:
//        def response = mvc
//                .perform(get("/kafka/get"))
//
//        then:
//        response.andExpect(status().isOk())
//        response.andExpect(content().string("Hello, World!"))
//    }
//
//    def "get mock projects"() {
//
//        when:
//        def response = mvc
//                .perform(get("/projects/alltest"))
//
//        then:
//        response.andExpect(status().isOk())
//        response.andExpect(jsonPath("\$").isArray())
//        response.andExpect(jsonPath("\$").value(hasSize(1)))
//    }
//
//    def "add a project"() {
//
//        def requestBody = "{" +
//                "    \"name\": \"Project A\"," +
//                "    \"description\": \"This is a new project\"," +
//                "    \"startDate\": \"2023-05-01T12:00:00\"," +
//                "    \"endDate\": \"2023-07-31T18:00:00\"," +
//                "    \"status\": \"PLANNED\"," +
//                "    \"ownerId\": 123," +
//                "    \"dateInfo\": {" +
//                "        \"createdAt\": \"2023-04-30T09:15:00\"," +
//                "        \"updatedAt\": \"2023-04-30T09:15:00\"" +
//                "    }," +
//                "    \"participantsEmails\": [" +
//                "        \"tenzoriator@rambler.ru\"" +
//                "    ]" +
//                "}"
//
//        def result = mvc.perform(post("/projects/add")
//                .contentType(APPLICATION_JSON)
//                .content(requestBody))
//                //.andExpect(status().isOk())
//                .andDo(print())
//                .andReturn()
//
//        expect: 1
//    }
//}
