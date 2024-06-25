package com.emida.auth.domain.interfaces;

import com.emida.auth.domain.entities.User;

public interface RegisterService {
    User register(String username, String password);
}
