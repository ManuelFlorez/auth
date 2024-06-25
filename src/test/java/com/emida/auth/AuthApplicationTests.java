package com.emida.auth;

import com.emida.auth.domain.interfaces.expose.ILoginAuthMaster;
import com.emida.auth.domain.interfaces.expose.IRegisterAuthMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	private IRegisterAuthMaster registerAuthMaster;

	 @Autowired
	 private ILoginAuthMaster loginAuthMaster;

	@Test
	void contextLoads() {
		assertNotNull(registerAuthMaster);
		assertNotNull(loginAuthMaster);
	}

	@Test
	void mainTest() {
		String[] args = {};
		AuthApplication.main(args);
		assertNotNull(args);
	}

}
