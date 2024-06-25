package com.emida.auth.infrastructure.adapters.services.user;

import com.emida.auth.domain.entities.User;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;

public class UserMapper {
    public User mapper(UserModel userModel) {
        return User.builder()
                .userName(userModel.getUserName())
                .password(userModel.getPassword())
                .build();
    }
}
