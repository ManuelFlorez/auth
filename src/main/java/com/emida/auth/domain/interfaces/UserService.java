package com.emida.auth.domain.interfaces;

import com.emida.auth.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User findByUserName(String username);
    UserDetailsService getUserDetailsService();
}
