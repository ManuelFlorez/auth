package com.emida.auth.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    private User user;
    private final int ID = 1;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";


    @BeforeEach
    void setUp() {
        user = User.builder().build();
        user.setId(ID);
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);
    }

    @Test
    void buildUser() {
        assertNotNull(user.toString());
        assertEquals(ID, user.getId());
        assertEquals(USERNAME, user.getUserName());
        assertEquals(PASSWORD, user.getPassword());
    }

}
