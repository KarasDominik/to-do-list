package com.karasdominik.task.web;

import com.karasdominik.common.LoggedUserProvider;
import com.karasdominik.common.LoggedUserProvider.LoggedUser;
import com.karasdominik.security.SecurityConfiguration;
import com.karasdominik.task.TaskManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static com.karasdominik.FileUtils.fetchJsonFrom;
import static java.util.UUID.randomUUID;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@WithMockUser(username = "support@gmail.com", password = "testPassword")
@Import(SecurityConfiguration.class)
class TaskControllerWebTest {

    private static final String PATH = "/api/v1/task";

    private static final LoggedUser LOGGED_USER = new LoggedUser(randomUUID());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskManagement management;
    @MockBean
    private LoggedUserProvider loggedUserProvider;

    @BeforeEach
    void setUp() {
        when(loggedUserProvider.getLoggedUser()).thenReturn(LOGGED_USER);
    }

    @Nested
    class CreateTests {

        private static final class Requests {
            private static final String REQUESTS_DIR = "src/test/resources/web/requests/task/create";
            private static final String INVALID_CONTENT_BLANK = REQUESTS_DIR + "/invalid-content-blank.json";
            private static final String INVALID_CONTENT_NULL = REQUESTS_DIR + "/invalid-content-null.json";
            private static final String INVALID_CONTENT_TOO_LONG = REQUESTS_DIR + "/invalid-content-too-long.json";
        }
    }

    @ParameterizedTest
    @MethodSource("invalidRequests")
    void shouldReturn400WhenContentIsBlank(String request, String errorMessage) throws Exception {
        var content = fetchJsonFrom(request);

        mockMvc.perform(post(PATH)
                        .content(content)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage", is(errorMessage)));
    }

    private static Stream<Arguments> invalidRequests() {
        return Stream.of(
                Arguments.of(
                        CreateTests.Requests.INVALID_CONTENT_BLANK, "Content cannot be blank"
                ),
                Arguments.of(
                        CreateTests.Requests.INVALID_CONTENT_NULL, "Content cannot be blank"
                ),
                Arguments.of(
                        CreateTests.Requests.INVALID_CONTENT_TOO_LONG, "Content can contain up to 80 characters"
                )
        );
    }
}
