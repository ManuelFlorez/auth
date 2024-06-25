package com.emida.auth.domain.interfaces.expose;

import com.emida.auth.domain.entities.User;

public interface IRegisterAuthMaster {
    String registerByUsernameAndPassword(User user);
}
