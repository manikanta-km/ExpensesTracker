package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product,Integer> {
}
