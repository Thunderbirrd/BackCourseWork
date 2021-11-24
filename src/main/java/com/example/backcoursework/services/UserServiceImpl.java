package com.example.backcoursework.services;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.User;
import com.example.backcoursework.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        user.setRating(0);
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

    @Override
    public String checkUser(String login, Integer id) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()){
            User user = optional.get();
            if (user.getLogin().equals(login)){
                return "User is valid";
            }
            return "User is not valid";
        }
        return "No user with such id";
    }

    @Override
    @Transactional
    public void decreaseRating(Integer id) {
        userRepo.decreaseRating(id);
    }

    @Override
    @Transactional
    public void increaseRating(Integer id) {
        userRepo.increaseRating(id);
    }

    @Override
    public Integer getRating(Integer id) {
        Optional<User> optional = userRepo.findById(id);
        return optional.map(User::getRating).orElse(null);
    }
}
