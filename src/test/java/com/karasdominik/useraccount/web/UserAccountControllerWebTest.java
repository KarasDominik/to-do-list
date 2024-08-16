package com.karasdominik.useraccount.web;

import com.karasdominik.security.SecurityConfiguration;
import com.karasdominik.useraccount.UserAccountManagement;
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
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAccountController.class)
@WithMockUser(username = "support@gmail.com", password = "testPassword")
@Import(SecurityConfiguration.class)
class UserAccountControllerWebTest {

    private static final String PATH = "/api/v1/user";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAccountManagement management;

    @Nested
    class CreateTests {

        private static final class Requests {
            private static final String REQUESTS_DIR = "src/test/resources/web/requests/user/create";

            private static final String INVALID_EMAIL_BLANK = REQUESTS_DIR + "/invalid-email-blank.json";
            private static final String INVALID_PASSWORD_BLANK = REQUESTS_DIR + "/invalid-password-blank.json";

            private static final String INVALID_EMAIL_NULL = REQUESTS_DIR + "/invalid-email-null.json";
            private static final String INVALID_PASSWORD_NULL = REQUESTS_DIR + "/invalid-password-null.json";

            private static final String INVALID_EMAIL_TOO_LONG = REQUESTS_DIR + "/invalid-email-too-long.json";
            private static final String INVALID_EMAIL_NO_AT = REQUESTS_DIR + "/invalid-email-no-at.json";
            private static final String INVALID_EMAIL_NO_DOMAIN = REQUESTS_DIR + "/invalid-email-no-domain.json";

            private static final String INVALID_PASSWORD_TOO_LONG = REQUESTS_DIR + "/invalid-password-too-long.json";
            private static final String INVALID_PASSWORD_TOO_SHORT = REQUESTS_DIR + "/invalid-password-too-short.json";
            private static final String INVALID_PASSWORD_NO_NUMBER = REQUESTS_DIR + "/invalid-password-no-number.json";
            private static final String INVALID_PASSWORD_NO_SPECIAL_CHARACTER = REQUESTS_DIR + "/invalid-password-no-special-character.json";
        }

        @ParameterizedTest
        @MethodSource("invalidRequests")
        void
        shouldNotCreateUserWhenRequestIsInvalid(String request, String errorMessage) throws Exception {
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
                    Arguments.of(Requests.INVALID_EMAIL_BLANK, "Email cannot be blank"),
                    Arguments.of(Requests.INVALID_PASSWORD_BLANK, "Password cannot be blank"),

                    Arguments.of(Requests.INVALID_EMAIL_NULL, "Email cannot be blank"),
                    Arguments.of(Requests.INVALID_PASSWORD_NULL, "Password cannot be blank"),

                    Arguments.of(Requests.INVALID_EMAIL_TOO_LONG, "Email can contain up to 50 characters"),
                    Arguments.of(Requests.INVALID_EMAIL_NO_AT, "Email is invalid"),
                    Arguments.of(Requests.INVALID_EMAIL_NO_DOMAIN, "Email is invalid"),

                    Arguments.of(Requests.INVALID_PASSWORD_TOO_LONG, "Password can contain up to 30 characters"),
                    Arguments.of(Requests.INVALID_PASSWORD_TOO_SHORT, "Password must contain at least 8 characters"),
                    Arguments.of(Requests.INVALID_PASSWORD_NO_NUMBER, "Password is invalid"),
                    Arguments.of(Requests.INVALID_PASSWORD_NO_SPECIAL_CHARACTER, "Password is invalid")

            );
        }
    }
}
