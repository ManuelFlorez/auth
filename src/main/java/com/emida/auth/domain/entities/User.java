package com.emida.auth.domain.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private long id;
    private String userName;
    private String password;
}
