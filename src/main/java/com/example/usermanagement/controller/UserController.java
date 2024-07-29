package com.example.usermanagement.controller;

import com.example.usermanagement.DTOs.UserCreateDTO;
import com.example.usermanagement.DTOs.UserDTO;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.usermanagement.DTOs.UserCreateDTO;
import com.example.usermanagement.DTOs.UserDTO;
import com.example.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management System", description = "Operations related to user management")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    @Operation(summary = "Get list of users")
    public List<UserDTO> getUsers(@RequestParam(required = false) String search,
                                  @RequestParam(defaultValue = "lastName") String[] sortBy,
                                  @RequestParam(defaultValue = "asc") String sortDirection) {
        return userService.getUsers(search, sortBy, sortDirection);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserCreateDTO user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}