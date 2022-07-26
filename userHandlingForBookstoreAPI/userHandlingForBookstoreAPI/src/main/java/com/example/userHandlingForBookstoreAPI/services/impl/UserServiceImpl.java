package com.example.userHandlingForBookstoreAPI.services.impl;

import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.entities.VerificationToken;
import com.example.userHandlingForBookstoreAPI.model.UserModel;
import com.example.userHandlingForBookstoreAPI.repositories.UserRepository;
import com.example.userHandlingForBookstoreAPI.repositories.VerificationTokenRepository;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
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
    public void saveVerificationTokenForUser(User user, String token) {
        var verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }
}
