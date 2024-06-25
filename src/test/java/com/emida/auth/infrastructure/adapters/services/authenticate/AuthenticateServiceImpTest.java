package com.emida.auth.infrastructure.adapters.services.authenticate;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.UserService;
import com.emida.auth.infrastructure.adapters.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticateServiceImpTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private AuthenticateServiceImp authenticateServiceImp;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .userName("username")
                .password("password")
                .build();
    }

    @Test
    void authenticateTest () {
        final String token = "token";

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userService.findByUserName(anyString())).thenReturn(user);
        when(jwtService.getToken(any(UserDetails.class))).thenReturn(token);

        final String tokenResult = authenticateServiceImp.authenticate(user);

        assertEquals(token, tokenResult);
    }

}
