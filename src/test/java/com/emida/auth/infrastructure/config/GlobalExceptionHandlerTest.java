package com.emida.auth.infrastructure.config;

import com.emida.auth.infrastructure.exception.ErrorDetails;
import com.emida.auth.infrastructure.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private ResourceNotFoundException resourceNotFoundException;
    private BadCredentialsException badCredentialsException;
    private ErrorDetails errorDetails;

    @BeforeEach
    void setUp() {
        resourceNotFoundException = new ResourceNotFoundException("");
        badCredentialsException = new BadCredentialsException("");
    }

    @Test
    void resourceNotFoundExceptionTest() {
        final ResponseEntity<ErrorDetails> response = globalExceptionHandler
                .resourceNotFoundException(resourceNotFoundException, webRequest);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void badCredentialsException() {
        final ResponseEntity<ErrorDetails> response = globalExceptionHandler
                .badCredentialsException(badCredentialsException, webRequest);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void globalExceptionHandlerTest() {
        final ResponseEntity<ErrorDetails> response = globalExceptionHandler
                .globalExceptionHandler(new RuntimeException(), webRequest);
        assertNotNull(response);
        assertTrue(response.getStatusCode().is5xxServerError());
    }

    @Test
    void buildErrorDetailsTest() {
        final String message = "error";
        final String details = "details";

        errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(message);
        errorDetails.setDetails(details);

        assertNotNull(errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }

}
