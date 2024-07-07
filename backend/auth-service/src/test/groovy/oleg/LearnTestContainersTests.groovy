package oleg

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import spock.lang.Ignore
import spock.lang.Specification

import java.sql.DriverManager

@Ignore
class LearnTestContainersTests {
//    extends Specification {

    @Autowired
    DriverManager driverManager

    static PostgreSQLContainer<?> pg
            = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:14.2"))
            .withDatabaseName("authservicedb")
            .withUsername("authservice")
            .withPassword("12345")

    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:14.2"))
            .withExposedPorts(5432)

    static {
        pg.start()
    }

    def "postgres testcontainer is not null"() {
        expect:
        pg != null
    }

    def "1"() {
        println postgresContainer
        expect:
        postgresContainer != null
    }

    def "2"() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(
//                DriverManager.getConnection(
//                        postgresContainer.jdbcUrl,
//                        postgresContainer.username,
//                        postgresContainer.password))
        println postgresContainer
        expect:
        1
    }
}