package com.emida.auth.infrastructure.adapters.services.register;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.UserRepository;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterServiceImp registerServiceImp;

    private User user;
    private final int ID = 0;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .build();
        UserModel userModel = new UserModel();
        userModel.setId(ID);
        userModel.setUserName(USERNAME);
        userModel.setPassword(PASSWORD);
    }

    @Test
    void registerTest() {
        when(passwordEncoder.encode(PASSWORD)).thenReturn("123456");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User userResult = registerServiceImp.register(USERNAME, PASSWORD);

        assertEquals(ID, userResult.getId());
        assertEquals(USERNAME, userResult.getUserName());
        assertEquals(PASSWORD, userResult.getPassword());
    }

}
