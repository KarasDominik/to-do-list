package com.karasdominik.task;

import lombok.Builder;

import java.util.UUID;

import static com.karasdominik.useraccount.UsersForTests.BOB;
import static com.karasdominik.useraccount.UsersForTests.SUPPORT;

public class TasksForTests {

    public static final UUID LEARN_REACT_ID = UUID.fromString("d7238512-c206-4927-b780-fb37efea4d04");
    public static final TaskData LEARN_REACT = new TaskData(LEARN_REACT_ID, "Learn React", BOB.userId(), false);

    public static final UUID FINISH_APP_ID = UUID.fromString("c6d9b871-5873-4360-9d1a-5701757c9bf5");
    public static final TaskData FINISH_APP = new TaskData(FINISH_APP_ID, "Finish to do list web application", BOB.userId(), false);

    public static final UUID WRITE_INTEGRATION_TESTS_ID = UUID.fromString("fb3d2a51-b67f-41e9-8586-01f575d34c7d");
    public static final TaskData WRITE_INTEGRATION_TESTS = new TaskData(WRITE_INTEGRATION_TESTS_ID, "Write integration tests", SUPPORT.userId(), true);


    @Builder
    public record TaskData(UUID taskId, String content, UUID userId, boolean done) {}
}
