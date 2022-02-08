package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dtos.SignInDTO;
import com.example.demo.models.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SignInDTO signIn = mapper.readValue(request.getInputStream(), SignInDTO.class);

        if (signIn == null) {
            throw new UsernameNotFoundException("Unable to authenticate");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(signIn.getUsername(),
                signIn.getPassword());
        return authenticationManager.authenticate(authentication);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        UserAccount userAccount = (UserAccount) authResult.getPrincipal();
        String token = jwtUtils.createToken(userAccount.getEmail());

        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
