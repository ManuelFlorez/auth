package com.emida.auth.infrastructure.receivers.entrypoints.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidControllerTest {

    private ValidController controller;

    @BeforeEach
    void setUp() {
        controller = new ValidController();
    }

    @Test
    void validToken() {
        ResponseEntity<String> response = controller.validToken();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals("OK", response.getBody());
    }

}
