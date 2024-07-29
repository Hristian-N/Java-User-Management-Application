package com.example.usermanagement.repository;

import com.example.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrEmailAddressContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase(
            String lastName, String firstName, String emailAddress, String phoneNumber);
    List<User> findByDateOfBirth(LocalDate dateOfBirth);
}