package com.emida.auth.infrastructure.adapters.jwt;

import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import com.emida.auth.infrastructure.adapters.services.authenticate.CustomUserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    private UserDetails user;

    @InjectMocks
    private JwtService jwtService;

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        userModel = new UserModel();
        userModel.setId(1);
        userModel.setUserName("username");
        userModel.setPassword("password");
        user = new CustomUserDetail(userModel);

        final String secretKey = "secretKey";
        final String value = "586E3272357538782F413F4428472B4B6250655368566B597033733676390000";
        ReflectionTestUtils.setField(jwtService, secretKey, value);
    }

    @Test
    void getTokenTest() {
        final String token = jwtService.getToken(user);
        assertNotNull(token);
    }

    @Test
    void getUsernameFromTokenTest() {
        final String token = jwtService.getToken(user);
        final String username = jwtService.getUsernameFromToken(token);
        assertNotNull(username);
    }

    @Test
    void isTokenValidTest() {
        final String token = jwtService.getToken(user);
        final UserDetails userDetails = new CustomUserDetail(userModel);

        final boolean isTokenValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isTokenValid);
    }


}
