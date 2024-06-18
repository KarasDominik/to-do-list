package com.karasdominik.it;

import com.karasdominik.task.TaskAssertions;
import com.karasdominik.task.TaskFixtures;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.karasdominik.FileUtils.fetchJsonFrom;
import static com.karasdominik.task.TasksForTests.FINISH_APP;
import static com.karasdominik.task.TasksForTests.LEARN_REACT;
import static com.karasdominik.task.TasksForTests.WRITE_INTEGRATION_TESTS;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {DockerizedDbInitializer.class})
class TaskControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskFixtures fixtures;
    @Autowired
    private TaskAssertions assertions;

    @BeforeEach
    void setUp() {
        fixtures.setUp();
    }

    @AfterEach
    void tearDown() {
        fixtures.tearDown();
    }

    @Nested
    class GetAllTests {

        @Test
        void shouldGetAllTasks() throws Exception {
            mockMvc.perform(get("/api/v1/task"))
                    .andDo(print())

                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$.tasks[0].taskId").value(FINISH_APP.taskId().toString()))
                    .andExpect(jsonPath("$.tasks[0].content").value(FINISH_APP.content()))
                    .andExpect(jsonPath("$.tasks[0].done").value(FINISH_APP.done()))

                    .andExpect(jsonPath("$.tasks[1].taskId").value(LEARN_REACT.taskId().toString()))
                    .andExpect(jsonPath("$.tasks[1].content").value(LEARN_REACT.content()))
                    .andExpect(jsonPath("$.tasks[1].done").value(LEARN_REACT.done()))

                    .andExpect(jsonPath("$.tasks[2].taskId").value(WRITE_INTEGRATION_TESTS.taskId().toString()))
                    .andExpect(jsonPath("$.tasks[2].content").value(WRITE_INTEGRATION_TESTS.content()))
                    .andExpect(jsonPath("$.tasks[2].done").value(WRITE_INTEGRATION_TESTS.done()));
        }
    }

    @Nested
    class CreateTests {

        private static final class Requests {
            private static final String VALID = "src/test/resources/web/requests/create/valid.json";
        }

        @Test
        void shouldCreateTask() throws Exception {
            var request = fetchJsonFrom(Requests.VALID);

            var result = mockMvc.perform(post("/api/v1/task")
                            .content(request)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.taskId", notNullValue()))
                    .andReturn();

            var response = result.getResponse().getContentAsString();

            var taskId = new JSONObject(response)
                    .getString("taskId");
            var content = new JSONObject(request)
                    .getString("content");

            assertions.assertTaskCreated(UUID.fromString(taskId), content);
        }
    }

    @Nested
    class UpdateTests {

        @Test
        void shouldUpdateTask() throws Exception {
            var taskId = FINISH_APP.taskId();

            mockMvc.perform(put("/api/v1/task/{taskId}", taskId))
                    .andExpect(status().isOk());

            assertions.assertTaskCompleted(taskId);
        }
    }

    @Nested
    class DeleteTests {

        @Test
        void shouldDeleteTask() throws Exception {
            var taskId = LEARN_REACT.taskId();

            mockMvc.perform(delete("/api/v1/task/{taskId}", taskId.toString()))
                    .andExpect(status().isOk());

            assertions.assertTaskDeleted(taskId);
        }
    }
}
