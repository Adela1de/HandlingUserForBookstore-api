package com.example.userHandlingForBookstoreAPI.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SaveResetPasswordEvent extends ApplicationEvent {

    private String token;
    private String oldPassword;
    private String newPassword;
    private String applicationUrl;

    public SaveResetPasswordEvent(String token, String oldPassword, String newPassword, String applicationUrl) {
        super(token);
        this.token = token;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.applicationUrl = applicationUrl;
    }
}
