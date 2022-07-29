package com.example.userHandlingForBookstoreAPI.events.listeners;

import com.example.userHandlingForBookstoreAPI.events.ResendVerificationTokenEvent;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResendVerificationTokenListener implements ApplicationListener<ResendVerificationTokenEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(ResendVerificationTokenEvent event) {
        String oldToken = event.getOldToken();
        var newVerificationToken = userService.generateNewVerificationToken(oldToken);

        String url = event.getApplicationUrl() + "/verifyRegistration?token="+newVerificationToken.getToken();

        log.info("click the link to verify your account: {}", url);

    }
}
