package com.emida.auth.infrastructure.adapters.h2.user.repository;

import com.emida.auth.infrastructure.adapters.h2.user.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface H2UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserName(@Param("userName") String userName);
}
