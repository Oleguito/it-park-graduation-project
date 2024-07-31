package oleg

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import ru.itpark.authservice.domain.jwt.JwtDefinition

import java.nio.charset.StandardCharsets;
import spock.lang.Specification

class ExtractJwtTests extends Specification {

    ObjectMapper objectMapper = new ObjectMapper();

    def "Try to decode a JWT" () {
        def jwt = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmsyMEV5RFJQVl9rbU5KM2pBaHZtUDdieDhMRUxmM0FTY09jRXVqV05ZIn0.eyJleHAiOjE3MjI0MjgwNjQsImlhdCI6MTcyMjQxMDA2NCwianRpIjoiMmU0ZDE1ZGMtYjBjZC00NTE0LThlYWItYjNmMDQ5ZWE0OGViIiwiaXNzIjoiaHR0cHM6Ly9sZW11ci03LmNsb3VkLWlhbS5jb20vYXV0aC9yZWFsbXMvZ3JhZC1wcm9qZWN0Iiwic3ViIjoiNTY4YjMwZmItZTM4ZS00ODYzLWIxMTgtNzY4YTVkY2U0YTMyIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYXV0aC1zZXJ2aWNlIiwic2lkIjoiYzkyNGM1NmItNjUwMy00NDU0LTk0ODMtMTE3YjQ5MGRjOTYyIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLWdyYWQtcHJvamVjdCJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImF1dGgtc2VydmljZSI6eyJyb2xlcyI6WyJhZG1pbiIsInVzZXIiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6Ik9sZWd1aXRvIFN3YWdidWNrcyIsInByZWZlcnJlZF91c2VybmFtZSI6Im9sZWd1aXRvIiwiZ2l2ZW5fbmFtZSI6Ik9sZWd1aXRvIiwiZmFtaWx5X25hbWUiOiJTd2FnYnVja3MiLCJlbWFpbCI6InRlbnpvcmlhdG9yQHJhbWJsZXIucnUifQ.YoMxaa372B9s7fy4CQWwkK9huYq6-mUUY6FrreWwtFv7evfO6BnYy336coo_rjhrAF9PIoRkpiEElhjR2X1a-ncZJEZMKe06wsDmIZZvo6kLyxXMJTtO3_ZKYUDWcAHWngCdPaZfS2v-HITzSAAiwMn8HDEWiU1MKAlGkV6AcEUQBMImBNAz735wd0ukq4islwLR1wMCuj9P_oAAr1RPALpM6wsWMYJXGDjC4q7zO0aTsK5ym1teopq54d_EKwv4LlaB78OXD3W-qe78ZwPfPcpbS_8w-jzCn9klIiyMjsxpyiEBC9XT1OY-Wgkl7p3KIPbGVf0jaWXWDESq_TFIng"
        def split = jwt.split("\\.")
        String base64EncodedHeader = split[0];
        String base64EncodedBody = split[1];
        String base64EncodedSignature = split[2];

        byte[] decodedPayload = Base64.getUrlDecoder().decode(base64EncodedBody)

        String payloadJson = new String(decodedPayload, StandardCharsets.UTF_8);
        println payloadJson

        JwtDefinition jwtdef = objectMapper.readValue(payloadJson, JwtDefinition.class);

        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        println jwtdef.toString()

        expect:
        jwtdef.resourceAccess.authService.roles.size() == 2
    }
}
