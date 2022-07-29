package com.example.userHandlingForBookstoreAPI.services.impl;

import com.example.userHandlingForBookstoreAPI.entities.PasswordResetToken;
import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.entities.VerificationToken;
import com.example.userHandlingForBookstoreAPI.exceptions.ObjectInvalidException;
import com.example.userHandlingForBookstoreAPI.exceptions.ObjectNotFoundException;
import com.example.userHandlingForBookstoreAPI.model.UserModel;
import com.example.userHandlingForBookstoreAPI.repositories.PasswordResetTokenRepository;
import com.example.userHandlingForBookstoreAPI.repositories.UserRepository;
import com.example.userHandlingForBookstoreAPI.repositories.VerificationTokenRepository;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel)
    {
        var user = new User();
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode( userModel.getPassword()) );
        user.setRole("USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token)
    {
        var verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        var verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) throw new ObjectInvalidException("Invalid token");

        var cal = Calendar.getInstance();

        if (verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            throw new ObjectInvalidException("Expired token");
        }

        var user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return "User successfully activated";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        var verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String createPasswordResetTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        var passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
        return token;
    }

    @Override
    public String validatePasswordResetToken(String token) {
        var passwordResetToken =
                passwordResetTokenRepository.
                findByToken(token);

        if (passwordResetToken == null) throw new ObjectNotFoundException("There is no registered token for this e-mail!");

        var cal = Calendar.getInstance();

        if (passwordResetToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Token expired";
        }

        return "valid";
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        var user =
                passwordResetTokenRepository.
                findByToken(token).getUser();
        if(user == null) new ObjectNotFoundException("There is no registered user for this token!");

        return user;
    }

    @Override
    public void changePassword(User user, String newPassword)
    {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

}
