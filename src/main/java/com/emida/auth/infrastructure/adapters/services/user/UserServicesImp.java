package com.emida.auth.infrastructure.adapters.services.user;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.UserService;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import com.emida.auth.infrastructure.adapters.h2.user.repository.H2UserRepository;
import com.emida.auth.infrastructure.adapters.services.authenticate.CustomUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServicesImp implements UserService {

    private static final String USER_NOT_FOUNT = "User not found";
    private final UserMapper userMapper = new UserMapper();
    private final H2UserRepository h2UserRepository;

    @Override
    public User findByUserName(String username) {
        UserModel userModel = h2UserRepository.findByUserName(username).orElseThrow();
        return userMapper.mapper(userModel);
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return username -> {
            UserModel user = h2UserRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUNT));
            return new CustomUserDetail(user);
        };
    }

}
