package com.example.userHandlingForBookstoreAPI.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String username;
    private String password;
    private String email;
    private String matchingPassword;
}
