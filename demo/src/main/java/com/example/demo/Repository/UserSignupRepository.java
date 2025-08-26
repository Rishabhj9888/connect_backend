package com.example.demo.Repository;

import com.example.demo.model.UserSignup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserSignupRepository extends JpaRepository<UserSignup,Long> {
    Optional<UserSignup> findByEmail(String Email);
}
