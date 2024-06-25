package com.emida.auth.infrastructure.adapters.traceability;

import com.emida.auth.domain.interfaces.TraceabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogImp implements TraceabilityService {

    private final Logger logger = LoggerFactory.getLogger(LogImp.class);

    @Override
    public void send(String message) {
        logger.info(message);
    }

    @Override
    public void sendError(String message) {
        logger.error(message);
    }
}
