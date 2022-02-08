package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;
import com.example.demo.repositories.CardRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorService extends WithAuthentication {
    private final CardRepository cardRepository;

    public List<Card> getAllCards() {
        UserAccount owner = getUserAccount();

        if (!owner.getRole().equals("Avaliador")) {
            throw new IllegalArgumentException("Apenas Clientes podem criar cards");
        }
        return cardRepository.findAll();
    }

}
