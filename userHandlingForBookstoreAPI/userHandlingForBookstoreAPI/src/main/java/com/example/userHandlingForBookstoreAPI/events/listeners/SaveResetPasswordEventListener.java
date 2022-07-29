package com.example.userHandlingForBookstoreAPI.events.listeners;

import com.example.userHandlingForBookstoreAPI.entities.User;
import com.example.userHandlingForBookstoreAPI.events.SaveResetPasswordEvent;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveResetPasswordEventListener implements ApplicationListener<SaveResetPasswordEvent> {

    private final UserService userService;
    @Override
    public void onApplicationEvent(SaveResetPasswordEvent event) {
        String result = userService.validatePasswordResetToken(event.getToken());

        if(!result.equalsIgnoreCase("valid")) throw new IllegalArgumentException("invalid user");
        Optional<User> user = userService.getUserByPasswordResetToken(result);
        if(user.isPresent()) userService.changePassword(user.get(), event.getOldPassword(), event.getNewPassword());

    }

}

