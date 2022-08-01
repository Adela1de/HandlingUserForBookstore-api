package com.example.oauthserver.services;

import com.example.oauthserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Bean
    public final PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(11); }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities(List.of(user.getRole()))
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role: roles)
        {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
