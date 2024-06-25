package com.emida.auth.infrastructure.receivers.entrypoints.api;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.expose.ILoginAuthMaster;
import com.emida.auth.domain.interfaces.expose.IRegisterAuthMaster;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.LoginRequestDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.response.AuthResponseDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.RegisterRequestDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.response.RegisterResponseDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper = new UserMapper();
    private final ILoginAuthMaster loginCtr;
    private final IRegisterAuthMaster registerCtr;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        final User user = userMapper.mapper(request);
        final String token = loginCtr.loginByUsernameAndPassword(user);
        final AuthResponseDTO authResponse = new AuthResponseDTO(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(value = "register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        final User user = userMapper.mapper(request);
        final String message = registerCtr.registerByUsernameAndPassword(user);
        final RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(message);
        return ResponseEntity.ok(registerResponseDTO);
    }

}