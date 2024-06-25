package com.emida.auth.infrastructure.adapters.traceability;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogImpTest {

    @InjectMocks
    private LogImp logImp;

    @Test
    void sendTest() {
        logImp.send("OK");
        assertNotNull(logImp);
    }

    @Test
    void senErrorTest() {
        logImp.sendError("ERROR");
        assertNotNull(logImp);
    }
}
