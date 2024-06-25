package com.emida.auth.domain.interfaces;

import com.emida.auth.domain.entities.User;

public interface UserRepository {
    User save(User user);
}