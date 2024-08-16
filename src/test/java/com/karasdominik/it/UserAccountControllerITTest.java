package com.karasdominik.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karasdominik.useraccount.UserAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

import static com.karasdominik.FileUtils.fetchJsonFrom;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;

class UserAccountControllerITTest extends BaseAbstractITTest {

    private static final String PATH = "/api/v1/user";

    @Autowired
    private UserAssertions assertions;

    @Nested
    class CreateTests {

        private static final class Requests {
            private static final String VALID = "src/test/resources/web/requests/user/create/valid.json";
        }

        @Test
        void shouldCreateUser() throws Exception {
            var request = fetchJsonFrom(Requests.VALID);

            var userId =
                    given()
                        .contentType(JSON)
                        .body(request)
                    .when()
                        .post(PATH)
                    .then()
                        .statusCode(SC_CREATED)
                        .extract()
                        .path("userId");

            HashMap<String, String> requestBody = new ObjectMapper().readValue(request, HashMap.class);

            assertions.assertUserCreated(UUID.fromString(userId.toString()), requestBody);
        }
    }
}
