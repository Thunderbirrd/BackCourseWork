package com.example.backcoursework.controllers.responses;

import com.example.backcoursework.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String message;
    private User user;
}
