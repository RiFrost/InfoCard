package com.webtech.service;

import com.webtech.model.User;
import com.webtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    //Exception sind noch verbesserbar!
    public User findById(Long userId) {
        Optional<User> maybeUser = userRepo.findById(userId);
        if (maybeUser.isEmpty()) {
            throw new IllegalArgumentException("Userobjekt ist leer");
        }
        User user = maybeUser.orElseThrow(() -> new IllegalArgumentException("User " + userId + " does not exist!"));
        return user;
    }
}
