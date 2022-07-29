package com.example.userHandlingForBookstoreAPI.services;

import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.entities.VerificationToken;
import com.example.userHandlingForBookstoreAPI.model.UserModel;

import java.util.Optional;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    String createPasswordResetTokenForUser(User user);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user,String oldPassword, String newPassword);
}
