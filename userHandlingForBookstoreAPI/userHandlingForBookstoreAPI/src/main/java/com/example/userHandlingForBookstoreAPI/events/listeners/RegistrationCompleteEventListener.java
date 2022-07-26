package com.example.userHandlingForBookstoreAPI.events.listeners;

import com.example.userHandlingForBookstoreAPI.events.RegistrationCompleteEvent;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event)
    {
        var user = event.getUser();
        var token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);

        String url = event.getApplicationUrl() + "verifyRegistration?token="+token;

        log.info("click the link to verify your account: {}", url);
    }
}
