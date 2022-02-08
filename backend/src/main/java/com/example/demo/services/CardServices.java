package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;
import com.example.demo.repositories.CardRepository;
import com.example.demo.repositories.UserAccountsRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardServices extends WithAuthentication {
    private final CardRepository cardRepository;
    private final UserAccountsRepository userAccountsRepository;

    public Card create(Card card) {
        UserAccount owner = getUserAccount();
        if (!owner.getRole().equals("Cliente")) {
            throw new IllegalArgumentException("Apenas Clientes podem criar cards");
        }
        Card _card = Card.builder().title(card.getTitle())
                .description(card.getDescription())
                .owner(owner)
                .build();

        return cardRepository.save(_card);
    }

    public List<Card> getCards() {
        UserAccount owner = getUserAccount();
        return cardRepository.findByOwner(owner);
    }

    public ResponseEntity<Card> approve(Card card, Long id) {

        UserAccount owner = getUserAccount();

        if (!owner.getRole().equals("Avaliador")) {
            throw new IllegalArgumentException("Apenas Administradores podem aprovar uma demanda");
        }

        Optional<Card> _card = cardRepository.findById(id);

        return _card.map(record -> {
            record.setApproved(card.isApproved());
            Card updated = cardRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Card> designa(UserAccount card, Long id) {

        UserAccount owner = getUserAccount();

        if (!owner.getRole().equals("Avaliador")) {
            throw new IllegalArgumentException("Apenas Administradores podem aprovar uma demanda");
        }

        Optional<Card> _card = cardRepository.findById(id);
        UserAccount dev = userAccountsRepository.findByEmail(card.getEmail()).get();

        return _card.map(record -> {
            record.setDeveloper(dev);
            Card updated = cardRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Card> update(Card card, Long id) {
        UserAccount owner = getUserAccount();

        if (!owner.getRole().equals("Avaliador")) {
            throw new IllegalArgumentException("Apenas Administradores podem aprovar uma demanda");
        }

        return cardRepository.findById(id)
                .map(record -> {
                    record.setTitle(card.getTitle());
                    record.setDescription(card.getDescription());
                    record.setPriority(card.getPriority());
                    // record.setDeveloper(dev);
                    record.setUpdatedAt(new Date());
                    Card updated = cardRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void delete(Long id) {
        UserAccount owner = getUserAccount();

        if (!owner.getRole().equals("Avaliador")) {
            throw new IllegalArgumentException("Apenas Administradores podem aprovar uma demanda");
        }

        cardRepository.deleteById(id);
    }
}
