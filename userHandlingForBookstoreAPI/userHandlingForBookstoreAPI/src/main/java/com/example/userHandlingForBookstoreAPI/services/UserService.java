package com.example.userHandlingForBookstoreAPI.services;

import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);
}
