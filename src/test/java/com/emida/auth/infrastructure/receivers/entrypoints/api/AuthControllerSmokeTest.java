package com.emida.auth.infrastructure.receivers.entrypoints.api;

import com.emida.auth.infrastructure.receivers.entrypoints.api.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthControllerSmokeTest {

    @Autowired
    private AuthController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }
}
