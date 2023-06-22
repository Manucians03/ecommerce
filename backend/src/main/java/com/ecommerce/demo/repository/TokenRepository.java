package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.AuthenticationToken;
import com.ecommerce.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}

