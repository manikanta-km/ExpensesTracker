package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Product;
import com.example.ExpenseTracker.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepo productRepo;

    public void createProductExpense(Product newProduct) {
        newProduct.setPurchaseDateTime(LocalDateTime.now());
        productRepo.save(newProduct);
    }


    public Product getProductById(Integer productId) {
        return  productRepo.findById(productId).orElseThrow();
    }

    public void removeById(Integer productId) {
        productRepo.deleteById(productId);
    }

    public void updateById(Integer productId, Integer price) {
        Product pProduct = productRepo.findById(productId).orElseThrow();
        pProduct.setProductPrice(price);
        productRepo.save(pProduct);
    }

    public Integer getExpenseById(Integer productId) {
        Product pProduct = productRepo.findById(productId).orElseThrow();
        return pProduct.getProductPrice();
    }

}
