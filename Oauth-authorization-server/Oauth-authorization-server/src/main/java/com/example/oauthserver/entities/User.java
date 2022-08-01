package com.example.oauthserver.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(length = 60)
    private String password;
    private String email;
    private String role;
    private boolean enabled = false;

    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
