package com.emida.auth.infrastructure.adapters.services.authenticate;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.AuthenticateService;
import com.emida.auth.domain.interfaces.UserService;
import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import com.emida.auth.infrastructure.adapters.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImp implements AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public String authenticate(User userDomain) {
        final String userName = userDomain.getUserName();
        final String password = userDomain.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        User user = userService.findByUserName(userName);
        UserModel userModel = mapper(user);
        UserDetails userDetails = new CustomUserDetail(userModel);
        return jwtService.getToken(userDetails);
    }

    private UserModel mapper(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setPassword(user.getPassword());
        return userModel;
    }
}
