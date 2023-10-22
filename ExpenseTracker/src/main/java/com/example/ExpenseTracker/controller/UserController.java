package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Product;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {
        return userService.userSignUp(newUser);
    }

    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }

    @PostMapping("expense")
    public String addExpense(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Product newProduct){
        return userService.addExpense(email,tokenValue,newProduct);
    }

    @PutMapping("expense/{productId}")
    public String updateExpense(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer productId, @RequestParam Integer price)
    {
        return userService.updateExpense(email,tokenValue,productId,price);
    }

    @DeleteMapping("expense/{productId}")
    public String deleteExpense(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer productId)
    {
        return userService.deleteExpense(email,tokenValue,productId);
    }

    @GetMapping("expense/{productId}")
    public String getExpense(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer productId)
    {
        return userService.getExpense(email,tokenValue,productId);
    }

    @GetMapping("expenses/byDate")
    public List<Product> getExpenseByDate(@RequestParam String email, @RequestParam String tokenValue, @RequestParam LocalDate date)
    {
        return userService.getExpenseByDate(email,tokenValue,date);
    }

    @GetMapping("expenses/byMonth")
    public List<Product> getExpenseByMonth(@RequestParam String email, @RequestParam String tokenValue, @RequestParam Integer month)
    {
        return userService.getExpenseByMonth(email,tokenValue,month);
    }





}
