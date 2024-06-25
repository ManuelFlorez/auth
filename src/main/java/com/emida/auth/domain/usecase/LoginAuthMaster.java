package com.emida.auth.domain.usecase;


import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.AuthenticateService;
import com.emida.auth.domain.interfaces.TraceabilityService;
import com.emida.auth.domain.interfaces.expose.ILoginAuthMaster;

public class LoginAuthMaster implements ILoginAuthMaster {

    private final AuthenticateService authenticateService;
    private final TraceabilityService traceabilityService;

    public LoginAuthMaster(AuthenticateService authenticateService,
                            TraceabilityService traceabilityService) {
        this.authenticateService = authenticateService;
        this.traceabilityService = traceabilityService;
    }

    @Override
    public String loginByUsernameAndPassword(User user) {
        final String token = authenticateService.authenticate(user);
        final String messageSuccessful = "successful: login %s token: %s";
        final String message = String.format(messageSuccessful, user.getUserName(), token);
        traceabilityService.send(message);
        return token;
    }

}
