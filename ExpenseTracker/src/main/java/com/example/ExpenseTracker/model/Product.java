package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private LocalDate purchaseDate;
    private LocalDateTime purchaseDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
}
