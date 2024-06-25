package com.emida.auth.infrastructure.receivers.entrypoints.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validToken")
public class ValidController {
    @PostMapping
    public ResponseEntity<String> validToken() {
        return ResponseEntity.ok("OK");
    }
}