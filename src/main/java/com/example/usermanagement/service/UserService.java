package com.example.usermanagement.service;

import com.example.usermanagement.DTOs.UserCreateDTO;
import com.example.usermanagement.DTOs.UserDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setDateOfBirth(userCreateDTO.getDateOfBirth());
        user.setPhoneNumber(userCreateDTO.getPhoneNumber());
        user.setEmailAddress(userCreateDTO.getEmailAddress());
        return convertToDTO(userRepository.save(user));
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public List<UserDTO> getUsers(String searchTerm, String[] sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.DESC;
        }

        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy != null) {
            for (String sortField : sortBy) {
                orders.add(new Sort.Order(direction, sortField));
            }
        }

        Sort sort = Sort.by(orders);

        List<User> users;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            List<User> result = new ArrayList<>();
            try {
                LocalDate dateOfBirth = LocalDate.parse(searchTerm);
                result.addAll(userRepository.findByDateOfBirth(dateOfBirth));
            } catch (Exception e) {
                // If parsing fails, it means searchTerm is not a date
            }
            result.addAll(userRepository.findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrEmailAddressContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase(
                    searchTerm, searchTerm, searchTerm, searchTerm));
            users = result;
        } else {
            users = userRepository.findAll(sort);
        }
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserCreateDTO userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setEmailAddress(userDetails.getEmailAddress());
        return convertToDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmailAddress(user.getEmailAddress());
        return userDTO;
    }
}
