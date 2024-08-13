package com.karasdominik.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karasdominik.useraccount.UserAssertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.UUID;

import static com.karasdominik.FileUtils.fetchJsonFrom;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {DockerizedDbInitializer.class})
@ActiveProfiles("test")
class UserAccountControllerITTest {

    private static final String PATH = "/api/v1/user";

    @Autowired
    private MockMvc mockMvc;

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

            var result = mockMvc.perform(post(PATH)
                            .content(request)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.userId", notNullValue()))
                    .andReturn();

            var response = result.getResponse().getContentAsString();

            var userId = new JSONObject(response)
                    .getString("userId");

            HashMap<String, String> requestBody = new ObjectMapper().readValue(request, HashMap.class);

            assertions.assertUserCreated(UUID.fromString(userId), requestBody);
        }
    }
}
