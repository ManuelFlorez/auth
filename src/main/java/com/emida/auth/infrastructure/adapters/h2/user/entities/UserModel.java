package com.emida.auth.infrastructure.adapters.h2.user.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class UserModel implements Serializable {
    @Id
    @Column( name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
