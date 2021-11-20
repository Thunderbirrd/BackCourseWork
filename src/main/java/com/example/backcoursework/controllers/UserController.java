package com.example.backcoursework.controllers;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.User;
import com.example.backcoursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResponse register(@RequestBody String userData) throws JSONException {
        User user = new User();
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        String password = data.getString("password");
        user.setLogin(login);
        user.setPassword(password);
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(@RequestBody String userData) throws JSONException {
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        String password = data.getString("password");
        return userService.login(login, password);
    }
}
