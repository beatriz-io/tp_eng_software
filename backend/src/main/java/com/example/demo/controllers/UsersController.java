package com.example.demo.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.config.security.JwtUtils;
import com.example.demo.dtos.SignUpDTO;
import com.example.demo.models.UserAccount;
import com.example.demo.services.UserAccountService;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserAccountService service;
    private final JwtUtils jwtUtils;

    @PostMapping
    public UserAccount create(@Valid @RequestBody SignUpDTO signUp, HttpServletResponse response) {
        UserAccount userAccount = service.create(signUp);

        String token = jwtUtils.createToken(userAccount.getEmail());

        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return userAccount;
    }

}
