package com.am.attendancemanagement.service;


import com.am.attendancemanagement.entity.User;
import com.am.attendancemanagement.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }



    // Get all users
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }



    // Get user by id
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found with id: " + id
                        )
                );

    }



    // Get user by username
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );

    }



    // Save user
    public User saveUser(User user) {

        return userRepository.save(user);

    }



    // Delete user
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);

    }

}