package com.example.demo.services;

import com.example.demo.dtos.SignUpDTO;
import com.example.demo.models.UserAccount;
import com.example.demo.repositories.UserAccountsRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.demo.services.WithAuthentication;

@Service
@RequiredArgsConstructor
public class UserAccountService extends WithAuthentication implements UserDetailsService {

    private final UserAccountsRepository repository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found for email \"" + email + "\""));
    }

    public UserAccount create(SignUpDTO signUp) {
        if (!signUp.getPassword().equals(signUp.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password does not match");
        }

        String password = passwordEncoder.encode(signUp.getPassword());

        UserAccount userAccount = UserAccount.builder()
                .name(signUp.getName())
                .email(signUp.getEmail())
                .password(password)
                .role(signUp.getRole())
                .build();

        return repository.save(userAccount);
    }

    public void delete() {
        UserAccount userAccount = getUserAccount();
        repository.delete(userAccount);

    }

}
