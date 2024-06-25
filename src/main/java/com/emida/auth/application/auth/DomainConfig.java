package com.emida.auth.application.auth;

import com.emida.auth.domain.interfaces.AuthenticateService;
import com.emida.auth.domain.interfaces.RegisterService;
import com.emida.auth.domain.interfaces.TraceabilityService;
import com.emida.auth.domain.interfaces.expose.ILoginAuthMaster;
import com.emida.auth.domain.interfaces.expose.IRegisterAuthMaster;
import com.emida.auth.domain.usecase.LoginAuthMaster;
import com.emida.auth.domain.usecase.RegisterAuthMaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public IRegisterAuthMaster getIRegister(RegisterService registerService, TraceabilityService traceabilityService) {
        return new RegisterAuthMaster(registerService, traceabilityService);
    }

    @Bean
    public ILoginAuthMaster getILogin(AuthenticateService authenticateService, TraceabilityService traceabilityService) {
        return new LoginAuthMaster(authenticateService, traceabilityService);
    }

}
