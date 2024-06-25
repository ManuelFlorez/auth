package com.emida.auth.infrastructure.adapters.h2.user.repository;

import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;

public class UserMapper {

    public static UserModel mapper(com.emida.auth.domain.entities.User user) {
        UserModel userR = new UserModel();
        userR.setUserName(user.getUserName());
        userR.setPassword(user.getPassword());
        return userR;
    }

    public static com.emida.auth.domain.entities.User mapper(UserModel user) {
        return com.emida.auth.domain.entities.User.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}