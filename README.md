# Expense Tracker Application

This repository contains the source code for an Expense Tracker application implemented in Java using the Spring Boot framework. The application allows users to sign up, sign in, add, update, delete, and retrieve expenses. It also provides functionality to retrieve expenses based on date and month.

## Table of Contents

- [Controller](#controller)
- [Model](#model)
- [Endpoints](#endpoints)

## Controller

### UserController

#### `userSignUp`

- **Endpoint:** `POST /user/signup`
- **Description:** Signs up a new user.
- **Request Body:**
  - `User`: User details to be signed up.
- **Response:** Returns a message indicating the success of the sign-up process.

#### `userSignIn`

- **Endpoint:** `POST /user/signIn/{email}/{password}`
- **Description:** Signs in a user.
- **Path Variables:**
  - `email`: User's email address.
  - `password`: User's password.
- **Response:** Returns a message indicating the success of the sign-in process.

#### `userSignOut`

- **Endpoint:** `DELETE /user/signOut`
- **Description:** Signs out a user.
- **Request Parameters:**
  - `email`: User's email address.
  - `token`: User's authentication token.
- **Response:** Returns a message indicating the success of the sign-out process.

#### `addExpense`

- **Endpoint:** `POST /expense`
- **Description:** Adds a new expense for a user.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
- **Request Body:**
  - `Product`: Details of the new expense.
- **Response:** Returns a message indicating the success of the expense addition.

#### `updateExpense`

- **Endpoint:** `PUT /expense/{productId}`
- **Description:** Updates an existing expense for a user.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
  - `productId`: ID of the expense to be updated.
  - `price`: New price for the expense.
- **Response:** Returns a message indicating the success of the expense update.

#### `deleteExpense`

- **Endpoint:** `DELETE /expense/{productId}`
- **Description:** Deletes an existing expense for a user.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
  - `productId`: ID of the expense to be deleted.
- **Response:** Returns a message indicating the success of the expense deletion.

#### `getExpense`

- **Endpoint:** `GET /expense/{productId}`
- **Description:** Retrieves details of a specific expense for a user.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
  - `productId`: ID of the expense to be retrieved.
- **Response:** Returns details of the specified expense.

#### `getExpenseByDate`

- **Endpoint:** `GET /expenses/byDate`
- **Description:** Retrieves a list of expenses for a user based on a specific date.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
  - `date`: Specific date for filtering expenses.
- **Response:** Returns a list of expenses filtered by the specified date.

#### `getExpenseByMonth`

- **Endpoint:** `GET /expenses/byMonth`
- **Description:** Retrieves a list of expenses for a user based on a specific month.
- **Request Parameters:**
  - `email`: User's email address.
  - `tokenValue`: User's authentication token.
  - `month`: Specific month for filtering expenses.
- **Response:** Returns a list of expenses filtered by the specified month.

