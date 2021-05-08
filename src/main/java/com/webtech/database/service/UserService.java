package com.webtech.database.service;

import com.webtech.database.model.User;
import com.webtech.database.repository.UserRepository;
import com.webtech.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public User findById(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new NotFoundException("UserId " + userId + " not found!"));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
