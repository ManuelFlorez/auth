package com.emida.auth.infrastructure.adapters.services.authenticate;

import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetail implements UserDetails {

    private final UserModel user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "USER";
        return List.of(new SimpleGrantedAuthority((role)));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
