package com.emida.auth.infrastructure.adapters.h2.user.repository;

import com.emida.auth.domain.entities.User;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImpTest {

    @Mock
    private H2UserRepository userRepository;

    @InjectMocks
    private UserRepositoryImp userRepositoryImp;

    private User user;

    private UserModel userModel;
    private final int id = 0;
    private final String username = "username";
    private final String password = "password";

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(id)
                .userName(username)
                .password(password)
                .build();
        userModel = new UserModel();
        userModel.setId(id);
        userModel.setUserName(username);
        userModel.setPassword(password);
    }

    @Test
    void saveUser() {
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);
        User userResult = userRepositoryImp.save(user);
        assertNotNull(userModel.toString());
        assertEquals(id, userModel.getId());
        assertNotNull(userResult);
        assertEquals(id, userResult.getId());
        assertEquals(username, userResult.getUserName());
        assertEquals(password, userResult.getPassword());
    }

}
