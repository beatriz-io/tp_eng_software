package com.example.demo.ServicesTest;

import com.example.demo.models.UserAccount;

import org.junit.jupiter.api.Test;

public class AvaliadorServiceTest {
    @Test
    public void should_return_exception_if_user_not_avaliador() {
        IllegalArgumentException exception = new IllegalArgumentException("Apenas Clientes podem criar cards");

        // UserAccount owner = new UserAccount(1, "fulano", );
    }

}
