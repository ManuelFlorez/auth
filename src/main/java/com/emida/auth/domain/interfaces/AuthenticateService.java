package com.emida.auth.domain.interfaces;

import com.emida.auth.domain.entities.User;

public interface AuthenticateService {
    String authenticate(User user);
}
