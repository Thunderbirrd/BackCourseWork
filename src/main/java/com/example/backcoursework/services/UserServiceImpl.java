package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.UserResponse;
import com.example.backcoursework.models.User;
import com.example.backcoursework.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse saveUser(User user) {
        UserResponse response = new UserResponse();
        if (findUserByLogin(user.getLogin()) != null) {
            response.setMessage("User with such login already exists");
            return response;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        response.setUser(userRepo.save(user));
        response.setMessage("Successful");
        return response;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    @Override
    public UserResponse login(String login, String password) {
        UserResponse response = new UserResponse();
        User user = findUserByLogin(login);
        if (user == null) {
            response.setMessage("Wrong login");
            return response;
        }
        if (passwordEncoder.matches(user.getPassword(), password)) {
            response.setUser(user);
            response.setMessage("Login successful");
        } else {
            response.setMessage("Wrong password");
        }
        return response;
    }
}
