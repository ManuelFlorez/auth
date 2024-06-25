package com.emida.auth.infrastructure.adapters.services.register;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.RegisterService;
import com.emida.auth.domain.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImp implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String username, String password) {
        final String passwordEncodeStr = passwordEncoder.encode(password);
        final User user = User.builder()
                .userName(username)
                .password(passwordEncodeStr)
                .build();
        return userRepository.save(user);
    }
}
