package com.example.userHandlingForBookstoreAPI.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ResetPasswordEvent extends ApplicationEvent {

    private String email;
    private String applicationUrl;

    public ResetPasswordEvent(String email, String applicationUrl)
    {
        super(email);
        this.email = email;
        this.applicationUrl = applicationUrl;
    }
}
