package com.emida.auth.infrastructure.receivers.entrypoints.api;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.expose.ILoginAuthMaster;
import com.emida.auth.domain.interfaces.expose.IRegisterAuthMaster;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.LoginRequestDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.RegisterRequestDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.response.AuthResponseDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.response.RegisterResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private ILoginAuthMaster loginCtr;

    @Mock
    private IRegisterAuthMaster registerCtr;

    @InjectMocks
    private AuthController controller;

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String TOKEN = "token";

    @Test
    void loginSuccessful() {
        when(loginCtr.loginByUsernameAndPassword(any(User.class))).thenReturn(TOKEN);
        final LoginRequestDTO request = new LoginRequestDTO(USERNAME, PASSWORD);
        final ResponseEntity<AuthResponseDTO> response = controller.login(request);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().token(), TOKEN);
    }

    @Test
    void registerSuccessful() {
        when(registerCtr.registerByUsernameAndPassword(any(User.class))).thenReturn(TOKEN);
        final RegisterRequestDTO request = new RegisterRequestDTO(USERNAME, PASSWORD);
        final ResponseEntity<RegisterResponseDTO> response = controller.register(request);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().message(), TOKEN);
    }

}
