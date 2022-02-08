package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;
import com.example.demo.repositories.CardRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DesenvolvedorService extends WithAuthentication {
    private final CardRepository cardRepository;

    public List<Card> getAllCardByDeveloper() {
        UserAccount developer = getUserAccount();

        if (!developer.getRole().equals("Desenvolvedor")) {
            throw new IllegalArgumentException("Apenas Clientes podem criar cards");
        }

        List<Card> a = cardRepository.findByDeveloper(developer);
        return a;
    }
}
