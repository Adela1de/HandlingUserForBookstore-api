package com.example.userHandlingForBookstoreAPI.events.listeners;

import com.example.userHandlingForBookstoreAPI.events.ResetPasswordEvent;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordEventListener implements ApplicationListener<ResetPasswordEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(ResetPasswordEvent event) {

        var user = userService.findUserByEmail(event.getEmail());
        String token = userService.createPasswordResetTokenForUser(user);

        String url = event.getApplicationUrl() + "/savePassword?token="+token;

        log.info("click the link to reset your password: {}", url);
    }
}
