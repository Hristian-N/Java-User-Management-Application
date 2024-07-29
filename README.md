# Setup and Installation

### Prerequisites

1. **Java Development Kit (JDK) 22.0.2**: Make sure you have JDK installed on your system.
2. **Maven 3.9.8**: You need Maven to build and run the project.
3. **PostgreSQL**: Install PostgreSQL on your system.

### Configuration

1. **Clone the Repository**
   ```shell
   https://github.com/Hristian-N/Java-User-Management-Application.git

2. **Create a PostgreSQL database** named `user_management`
3. **Configure application.properties**:
   Replace `your_db_username` and `your_db_password` with your actual PostgreSQL username and password.

# User API Documentation

## Overview

This API provides endpoints to manage users, including creating, retrieving, updating, and deleting user information.

## Base URL

`/api/users`

## Endpoints

### Create User

**URL:** `/api/users`

**Method:** `POST`

**Description:** Creates a new user.

**Request Body:**
- `firstName` (string): The first name of the user.
- `lastName` (string): The last name of the user.
- `dateOfBirth` (string): The date of birth of the user.
- `phoneNumber` (string): The phone number of the user.
- `emailAddress` (string): The email address of the user.

**Response Body:**
- `id` (number): The unique identifier of the user.
- `firstName` (string): The first name of the user.
- `lastName` (string): The last name of the user.
- `dateOfBirth` (string): The date of birth of the user.
- `phoneNumber` (string): The phone number of the user.
- `emailAddress` (string): The email address of the user.

### Get User by ID

**URL:** `/api/users/{id}`

**Method:** `GET`

**Description:** Retrieves a user by their ID.

**Path Parameters:**
- `id`: The ID of the user to retrieve (type: `Long`).

**Response Body:**
- `id` (number): The unique identifier of the user.
- `firstName` (string): The first name of the user.
- `lastName` (string): The last name of the user.
- `dateOfBirth` (string): The date of birth of the user.
- `phoneNumber` (string): The phone number of the user.
- `emailAddress` (string): The email address of the user.


### Get Users

**URL:** `/api/users`

**Method:** `GET`

**Description:** Retrieves a list of users with optional search and sorting.

**Query Parameters:**
- `search` (optional, string): A search term to filter users by.
- `sortBy` (optional, string[]): A list of fields to sort by (default: `lastName`, could be also `dateOfBirth`).
- `sortDirection` (optional, string): The direction of sorting, either `asc` for ascending or `desc` for descending (default: `asc`).

**Response:**
- **Body:** A JSON array of user objects, each containing the following fields:
    - `id` (number): The unique identifier of the user.
    - `firstName` (string): The first name of the user.
    - `lastName` (string): The last name of the user.
    - `dateOfBirth` (string): The date of birth of the user.
    - `phoneNumber` (string): The phone number of the user.
    - `emailAddress` (string): The email address of the user.

**Example:** `http://localhost:8080/api/users?sortBy=lastName&sortBy=dateOfBirth&sortDirection=asc`

### Update User

**URL:** `/api/users/{id}`

**Method:** `PUT`

**Description:** Updates an existing user.

**Path Parameters:**
- `id`: The ID of the user to update (type: `Long`).

**Request Body:**
The request body must contain a JSON object with the following fields:
- `firstName` (string): The first name of the user.
- `lastName` (string): The last name of the user.
- `dateOfBirth` (string): The date of birth of the user.
- `phoneNumber` (string): The phone number of the user.
- `emailAddress` (string): The email address of the user.

**Response:**
- **Body:** A JSON object representing the updated user with the following fields:
    - `id` (number): The unique identifier of the user.
    - `firstName` (string): The first name of the user.
    - `lastName` (string): The last name of the user.
    - `dateOfBirth` (string): The date of birth of the user.
    - `phoneNumber` (string): The phone number of the user.
    - `emailAddress` (string): The email address of the user.

### Delete User

**URL:** `/api/users/{id}`

**Method:** `DELETE`

**Description:** Deletes a user by their ID.

**Path Parameters:**
- `id`: The ID of the user to delete (type: `Long`).
