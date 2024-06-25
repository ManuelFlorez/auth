package com.emida.auth.domain;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.RegisterService;
import com.emida.auth.domain.interfaces.TraceabilityService;
import com.emida.auth.domain.usecase.RegisterAuthMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
class RegisterAuthMasterTest {

    @Mock
    private RegisterService registerService;
    @Mock
    private TraceabilityService traceabilityService;
    @InjectMocks
    private RegisterAuthMaster registerAuthMaster;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(0)
                .userName("username")
                .password("password")
                .build();
    }

    @Test
    void registerByUsernameAndPasswordTest() {
        final String MESSAGE = "successful: register username, login required";
        when(registerService.register(anyString(), anyString())).thenReturn(user);
        final String token = registerAuthMaster.registerByUsernameAndPassword(user);
        assertNotNull(token);
        assertEquals(MESSAGE, token);
    }
}
