package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);

}
