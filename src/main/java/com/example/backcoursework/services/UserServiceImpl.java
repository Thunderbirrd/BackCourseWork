package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
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
    public BaseResponse saveUser(User user) {
        BaseResponse response = new BaseResponse();
        if (findUserByLogin(user.getLogin()) != null) {
            response.setMessage("User with such login already exists");
            return response;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        response.setId(userRepo.save(user).getId());
        response.setMessage("Successful");
        return response;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    @Override
    public BaseResponse login(String login, String password) {
        BaseResponse response = new BaseResponse();
        User user = findUserByLogin(login);
        if (user == null) {
            response.setMessage("Wrong login");
            return response;
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            response.setId(user.getId());
            response.setMessage("Login successful: " + user.getLogin());
        } else {
            response.setMessage("Wrong password");
        }
        return response;
    }
}
