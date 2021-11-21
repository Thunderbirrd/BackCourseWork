package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    BaseResponse saveUser(User user);

    User findUserByLogin(String login);

    BaseResponse login(String login, String password);

    String checkUser(String login, Integer id);

    void decreaseRating(Integer id);

    void increaseRating(Integer id);
}
