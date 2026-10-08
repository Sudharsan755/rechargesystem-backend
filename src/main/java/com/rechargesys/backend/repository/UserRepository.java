package com.rechargesys.backend.repository;

import com.rechargesys.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByPhoneAndPassword(String phone, String password);
    boolean existsByPhone(String phone);
}

