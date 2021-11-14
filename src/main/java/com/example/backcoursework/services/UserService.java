package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.UserResponse;
import com.example.backcoursework.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse saveUser(User user);
    User findUserByLogin(String login);
    UserResponse login(String login, String password);
}
