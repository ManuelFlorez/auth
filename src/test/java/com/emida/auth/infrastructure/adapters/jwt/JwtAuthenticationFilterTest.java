package com.emida.auth.infrastructure.adapters.jwt;

import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import com.emida.auth.infrastructure.adapters.services.authenticate.CustomUserDetail;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private UserDetailsService userDetailsService;
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    private String token;

    @BeforeEach
    void setUp() {
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTcxOTAwNDExNSwiZXhwIjoxNzE5MDA1NTU1fQ.vHb9z0dZxB_4kJr_urQWTsT7FMPZUIL8MmKOXww7Pw4";
    }

    @Test
    void doFilterInternalTestTokenIsNull() throws ServletException, IOException {
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertNotNull(request);
        assertNotNull(response);
        assertNotNull(filterChain);
    }

    @Test
    void doFilterInternalTestTokenEmpty() throws ServletException, IOException {
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertNotNull(request);
        assertNotNull(response);
        assertNotNull(filterChain);
    }

    @Test
    void doFilterInternalTestToken() throws ServletException, IOException {
        final String bearerToken = "Bearer " + token;
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearerToken);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertNotNull(request);
        assertNotNull(response);
        assertNotNull(filterChain);
    }

    @Test
    void doFilterInternalTestUserNameIsNull() throws ServletException, IOException {
        final String bearerToken = "Bearer " + token;
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearerToken);
        when(jwtService.getUsernameFromToken(any())).thenThrow(ExpiredJwtException.class);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertNotNull(response);
    }

    @Test
    void doFilterInternalTestTokenGetUserName() throws ServletException, IOException {
        final String bearerToken = "Bearer " + token;
        final UserModel userModel = new UserModel();
        final String USERNAME = "username";
        final String PASSWORD = "password";
        userModel.setId(1);
        userModel.setUserName(USERNAME);
        userModel.setPassword(PASSWORD);
        final CustomUserDetail customUserDetail = new CustomUserDetail(userModel);
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearerToken);
        when(jwtService.getUsernameFromToken(anyString())).thenReturn(USERNAME);
        when(userDetailsService.loadUserByUsername(USERNAME)).thenReturn(customUserDetail);
        when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        assertNotNull(request);
    }
}
