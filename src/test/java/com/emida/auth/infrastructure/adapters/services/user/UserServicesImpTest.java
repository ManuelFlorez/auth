package com.emida.auth.infrastructure.adapters.services.user;

import com.emida.auth.domain.entities.User;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import com.emida.auth.infrastructure.adapters.h2.user.repository.H2UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServicesImpTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private H2UserRepository h2UserRepository;

    @InjectMocks
    private UserServicesImp userServicesImp;

    private UserModel userModel;
    private final int ID = 0;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        userModel = new UserModel();
        userModel.setId(ID);
        userModel.setUserName(USERNAME);
        userModel.setPassword(PASSWORD);
    }

    @Test
    void findByUserNameTest() {
        final Optional<UserModel> optionalUserModel = Optional.of(userModel);
        when(h2UserRepository.findByUserName(anyString())).thenReturn(optionalUserModel);

        final User userResult = userServicesImp.findByUserName(USERNAME);

        assertEquals(ID, userResult.getId());
        assertEquals(USERNAME, userResult.getUserName());
        assertEquals(PASSWORD, userResult.getPassword());
    }

    @Test
    void getUserDetailsService() {
        final Optional<UserModel> optionalUserModel = Optional.of(userModel);
        when(h2UserRepository.findByUserName(anyString())).thenReturn(optionalUserModel);

        final UserDetailsService userDetailsService = userServicesImp.getUserDetailsService();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(USERNAME);

        assertNotNull(userDetailsService);
        assertNotNull(userDetailsService.loadUserByUsername(USERNAME));
        assertNotNull(userDetails);
        assertNotNull(userDetails.getAuthorities());
        assertNotNull(userDetails.getPassword());
        assertNotNull(userDetails.getUsername());
    }
}
