package com.example.backcoursework.controllers;

import com.example.backcoursework.controllers.responses.BaseResponse;
import com.example.backcoursework.models.User;
import com.example.backcoursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io"})
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
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io"})
    public BaseResponse login(@RequestBody String userData) throws JSONException {
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        String password = data.getString("password");
        return userService.login(login, password);
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io"})
    public String checkUser(@RequestBody String userData){
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        Integer id = data.getInt("id");
        return userService.checkUser(login, id);
    }

    @RequestMapping(value = "/getRating", method = RequestMethod.POST)
    @CrossOrigin(originPatterns = {"http://localhost:63342", "https://thunderbirrd.github.io"})
    public Integer getRating(@RequestBody String userData){
        JSONObject data = new JSONObject(userData);
        Integer id = data.getInt("id");
        return userService.getRating(id);
    }
}
