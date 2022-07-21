package com.example.userHandlingForBookstoreAPI.controllers;

import com.example.userHandlingForBookstoreAPI.model.UserModel;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel)
    {
        var user = userService.registerUser(userModel);
        return "Success";
    }
}
