package com.example.demo.services;

import com.example.demo.models.UserAccount;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WithAuthentication {

    protected final Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected final UserAccount getUserAccount() {
        return (UserAccount) getAuthentication().getPrincipal();
    }

}
