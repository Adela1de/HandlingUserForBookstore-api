package com.example.userHandlingForBookstoreAPI.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ResendVerificationTokenEvent extends ApplicationEvent {

    private String oldToken;
    private String applicationUrl;

    public ResendVerificationTokenEvent(String oldToken, String applicationUrl)
    {
        super(oldToken);
        this.oldToken = oldToken;
        this.applicationUrl = applicationUrl;
    }
}
