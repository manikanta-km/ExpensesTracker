package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);

}
