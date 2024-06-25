package com.emida.auth.infrastructure.receivers.entrypoints.api.mapper;

import com.emida.auth.domain.entities.User;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.LoginRequestDTO;
import com.emida.auth.infrastructure.receivers.entrypoints.api.dtos.request.RegisterRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapper(LoginRequestDTO loginRequestDTO) {
        return User.builder()
                .userName(loginRequestDTO.userName())
                .password(loginRequestDTO.password())
                .build();
    }
    public User mapper(RegisterRequestDTO registerRequestDTO) {
        return User.builder()
                .userName(registerRequestDTO.userName())
                .password(registerRequestDTO.password())
                .build();
    }
}
