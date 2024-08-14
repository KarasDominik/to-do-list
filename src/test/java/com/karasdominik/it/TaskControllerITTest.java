package com.karasdominik.it;

import com.karasdominik.task.TaskAssertions;
import com.karasdominik.task.TaskFixtures;
import com.karasdominik.useraccount.UserFixtures;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.karasdominik.FileUtils.fetchJsonFrom;
import static com.karasdominik.task.TasksForTests.FINISH_APP;
import static com.karasdominik.task.TasksForTests.LEARN_REACT;
import static com.karasdominik.task.TasksForTests.WRITE_INTEGRATION_TESTS;
import static com.karasdominik.useraccount.UsersForTests.BOB;
import static com.karasdominik.useraccount.UsersForTests.SUPPORT;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.is;

class TaskControllerITTest extends BaseAbstractITTest {

    private static final String PATH = "/api/v1/task";
    private static final String SINGLE_PATH = "/api/v1/task/{id}";

    @Autowired
    private TaskFixtures tasks;
    @Autowired
    private UserFixtures users;

    @Autowired
    private TaskAssertions taskAssertions;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        users.setUp();
        tasks.setUp();
    }

    @Override
    @AfterEach
    void tearDown() {
        tasks.tearDown();
        super.tearDown();
    }

    @Nested
    class GetAllTests {

        @Test
        void shouldGetAllTasks() {
            given()
                .auth()
                .basic(SUPPORT.email().value(), SUPPORT.decodedPassword().value())
            .when()
                .get(PATH)
            .then()
                .statusCode(SC_OK)
                .body("tasks.size()", is(3))

                .body("tasks[0].taskId", is(FINISH_APP.taskId().toString()))
                .body("tasks[0].content", is(FINISH_APP.content()))
                .body("tasks[0].done", is(FINISH_APP.done()))

                .body("tasks[1].taskId", is(LEARN_REACT.taskId().toString()))
                .body("tasks[1].content", is(LEARN_REACT.content()))
                .body("tasks[1].done", is(LEARN_REACT.done()))

                .body("tasks[2].taskId", is(WRITE_INTEGRATION_TESTS.taskId().toString()))
                .body("tasks[2].content", is(WRITE_INTEGRATION_TESTS.content()))
                .body("tasks[2].done", is(WRITE_INTEGRATION_TESTS.done()));
        }
    }

    @Nested
    class CreateTests {

        private static final class Requests {
            private static final String VALID = "src/test/resources/web/requests/task/create/valid.json";
        }

        @Test
        void shouldCreateTask() throws Exception {
            var request = fetchJsonFrom(Requests.VALID);

            var taskId =
                    given()
                        .auth()
                        .basic(SUPPORT.email().value(), SUPPORT.decodedPassword().value())
                    .when()
                        .contentType(JSON)
                        .body(request)
                        .post(PATH)
                    .then().statusCode(SC_CREATED)
                        .extract()
                        .path("taskId");

            var content = new JSONObject(request)
                    .getString("content");

            taskAssertions.assertTaskCreated(UUID.fromString(taskId.toString()), content);
        }
    }

    @Nested
    class UpdateTests {

        @Test
        void shouldUpdateTask() {
            var taskId = FINISH_APP.taskId();

            given()
                .auth()
                .basic(BOB.email().value(), BOB.decodedPassword().value())
            .when()
                .put(SINGLE_PATH, taskId)
            .then()
                .statusCode(SC_OK);

            taskAssertions.assertTaskCompleted(taskId);
        }
    }

    @Nested
    class DeleteTests {

        @Test
        void shouldDeleteTask() {
            var taskId = LEARN_REACT.taskId();

            given()
                .auth()
                .basic(SUPPORT.email().value(), SUPPORT.decodedPassword().value())
            .when()
                .delete(SINGLE_PATH, taskId)
            .then()
                .statusCode(SC_OK);

            taskAssertions.assertTaskDeleted(taskId);
        }
    }
}
