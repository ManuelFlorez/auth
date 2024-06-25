package com.emida.auth.infrastructure.receivers.entrypoints.api;

import com.emida.auth.infrastructure.receivers.entrypoints.api.ValidController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ValidControllerSmokeTest {

    @Autowired
    private ValidController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

}
