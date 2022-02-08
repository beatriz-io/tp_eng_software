package com.example.demo.controllers;

import java.util.List;

import com.example.demo.services.CardServices;
import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardServices cardServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Card create(@RequestBody Card card) {
        return cardServices.create(card);
    }

    @GetMapping
    public List<Card> getCards() {
        return cardServices.getCards();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Card> approve(@RequestBody Card card, @PathVariable("id") Long id) {
        return cardServices.approve(card, id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Card> update(@RequestBody Card card, @PathVariable("id") Long id) {
        return cardServices.update(card, id);
    }

    @PatchMapping(value = "/designa/{id}")
    public ResponseEntity<Card> designa(@RequestBody UserAccount user, @PathVariable("id") Long id) {
        return cardServices.designa(user, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        cardServices.delete(id);
    }
}
