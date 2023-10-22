package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.AuthenticationToken;
import com.example.ExpenseTracker.model.Product;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.repo.IUserRepo;
import com.example.ExpenseTracker.service.emailutility.EmailHandler;
import com.example.ExpenseTracker.service.hashingutility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ProductService productService;


    public String userSignUp(User newUser) {
        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(newUser);
            return "User Registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignIn(String email, String password) {
        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);

                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }


    public String userSignOut(String email, String token) {
        if(authenticationService.authenticate(email,token)) {
            authenticationService.deleteToken(token);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public String addExpense(String email, String tokenValue, Product newProduct) {
        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            newProduct.setUser(existingUser);

            productService.createProductExpense(newProduct);
            return "Expense Added";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public String deleteExpense(String email, String tokenValue, Integer productId) {
        if(authenticationService.authenticate(email,tokenValue)) {

            Product preProduct =  productService.getProductById(productId);
            String  productOwnerEmail =  preProduct.getUser().getUserEmail();

            if(email.equals(productOwnerEmail))
            {
                productService.removeById(productId);
                return "Expense Removed!!";
            }
            else {
                return "Un authorized access!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public String updateExpense(String email, String tokenValue, Integer productId, Integer price) {
        if(authenticationService.authenticate(email,tokenValue)) {

            Product preProduct =  productService.getProductById(productId);
            String  productOwnerEmail =  preProduct.getUser().getUserEmail();

            if(email.equals(productOwnerEmail))
            {
                productService.updateById(productId,price);
                return "Expense Updated!!";
            }
            else {
                return "Un authorized access!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String getExpense(String email, String tokenValue, Integer productId) {
        if(authenticationService.authenticate(email,tokenValue)) {

            Product preProduct =  productService.getProductById(productId);
            String  productOwnerEmail =  preProduct.getUser().getUserEmail();

            if(email.equals(productOwnerEmail))
            {
                Integer price = productService.getExpenseById(productId);
                return price.toString();
            }
            else {
                return "Un authorized access!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public List<Product> getExpenseByDate(String email, String tokenValue, LocalDate date) {
        List<Product> expenses  = new ArrayList<>();
        if(authenticationService.authenticate(email,tokenValue)) {
            for(Product product : productService.productRepo.findAll()){
                if(product.getUser().getUserEmail().equals(email)){
                    if(product.getPurchaseDate().equals(date)){
                        expenses.add(product);
                    }
                }
            }
        }
        return expenses;
    }

    public List<Product> getExpenseByMonth(String email, String tokenValue, Integer month) {
        List<Product> expenses  = new ArrayList<>();
        if(authenticationService.authenticate(email,tokenValue)) {
            for(Product product : productService.productRepo.findAll()){
                if(product.getUser().getUserEmail().equals(email)){
                    LocalDate date = product.getPurchaseDate();
                    int pMonth = date.getMonthValue();
                    if(pMonth == month){
                        expenses.add(product);
                    }
                }
            }
        }
        return expenses;
    }
}
