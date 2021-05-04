package com.webtech.service;

import com.webtech.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.webtech.model.User;

public class LoginService {

  private User user;

  @Autowired
  private UserRepository userRepository;

  public LoginService(User user) {
    this.user = user;
  }
}
