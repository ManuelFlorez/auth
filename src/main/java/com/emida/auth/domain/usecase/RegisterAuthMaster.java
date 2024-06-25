package com.emida.auth.domain.usecase;

import com.emida.auth.domain.entities.User;
import com.emida.auth.domain.interfaces.RegisterService;
import com.emida.auth.domain.interfaces.TraceabilityService;
import com.emida.auth.domain.interfaces.expose.IRegisterAuthMaster;

public class RegisterAuthMaster implements IRegisterAuthMaster {

    private final RegisterService registerService;
    private final TraceabilityService traceabilityService;

    public RegisterAuthMaster(RegisterService registerService,
                              TraceabilityService traceabilityService) {
        this.registerService = registerService;
        this.traceabilityService = traceabilityService;
    }

    @Override
    public String registerByUsernameAndPassword(User user) {
        final User userSave = registerService.register(user.getUserName(), user.getPassword());
        final String messageSuccessful = "successful: register %s, login required";
        final String message = String.format(messageSuccessful, userSave.getUserName());
        traceabilityService.send(message);
        return message;
    }
}
