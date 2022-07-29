package com.example.userHandlingForBookstoreAPI.services;

import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.entities.VerificationToken;
import com.example.userHandlingForBookstoreAPI.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    String createPasswordResetTokenForUser(User user);

    String validatePasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    void resetPassword(User user, String newPassword);

    void changePassword(User user, String oldPassword, String newPassword);

}
