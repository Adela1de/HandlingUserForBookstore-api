package com.example.userHandlingForBookstoreAPI.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModel {

    private String email;
    private String oldPassword;
    private String newPassword;

}
