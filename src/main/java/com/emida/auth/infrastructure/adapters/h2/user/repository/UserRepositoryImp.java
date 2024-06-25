package com.emida.auth.infrastructure.adapters.h2.user.repository;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.UserRepository;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

    private final H2UserRepository userRepository;

    @Override
    public User save(User user) {
        UserModel userDb = UserMapper.mapper(user);
        userDb = userRepository.save(userDb);
        return UserMapper.mapper(userDb);
    }
}