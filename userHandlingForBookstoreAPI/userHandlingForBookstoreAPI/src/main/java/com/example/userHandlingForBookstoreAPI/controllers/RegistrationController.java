package com.example.userHandlingForBookstoreAPI.controllers;

import com.example.userHandlingForBookstoreAPI.events.RegistrationCompleteEvent;
import com.example.userHandlingForBookstoreAPI.events.ResendVerificationTokenEvent;
import com.example.userHandlingForBookstoreAPI.events.ResetPasswordEvent;
import com.example.userHandlingForBookstoreAPI.model.PasswordModel;
import com.example.userHandlingForBookstoreAPI.model.UserModel;
import com.example.userHandlingForBookstoreAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/users")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request)
    {
        var user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success";
    }



    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token)
    {
        var result = userService.validateVerificationToken(token);
        return result;
    }

    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          final HttpServletRequest request)
    {
        publisher.publishEvent(new ResendVerificationTokenEvent(oldToken, applicationUrl(request)));
        return "Verification Token resent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel,
                                final HttpServletRequest request)
    {
        publisher.publishEvent(new ResetPasswordEvent(passwordModel.getEmail(), applicationUrl(request)));
        return "Reset link sent";
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                                @RequestBody PasswordModel passwordModel)
    {
        userService.validatePasswordResetToken(token);
        var user = userService.getUserByPasswordResetToken(token);
        userService.resetPassword(user, passwordModel.getNewPassword());
        return "Password Reset";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel)
    {
        var user = userService.findUserByEmail(passwordModel.getEmail());
        userService.changePassword(user, passwordModel.getOldPassword(), passwordModel.getNewPassword());
        return "Password changed";
    }

    private String applicationUrl(HttpServletRequest request)
    {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
