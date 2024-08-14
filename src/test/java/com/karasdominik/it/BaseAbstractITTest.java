package com.karasdominik.it;

import com.karasdominik.useraccount.UserFixtures;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {DockerizedDbInitializer.class})
@ActiveProfiles("test")
abstract class BaseAbstractITTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserFixtures users;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void tearDown() {
        users.tearDown();
    }
}
