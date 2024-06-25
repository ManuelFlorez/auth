package com.emida.auth.domain.interfaces;

public interface TraceabilityService {
    void send(String message);
    void sendError(String message);
}
