package com.emida.auth.domain;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.AuthenticateService;
import com.emida.auth.domain.interfaces.TraceabilityService;
import com.emida.auth.domain.usecase.LoginAuthMaster;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginAuthMasterTest {

    @Mock
    private AuthenticateService authenticateService;

    @Mock
    private TraceabilityService traceabilityService;

    @InjectMocks
    private LoginAuthMaster loginAuthMaster;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userName("username")
                .password("password")
                .build();
    }

    @Test
    void loginByUsernameAndPassword() {
        when(authenticateService.authenticate(user)).thenReturn("token");
        final String token = loginAuthMaster.loginByUsernameAndPassword(user);
        assertNotNull(token);
    }

}
