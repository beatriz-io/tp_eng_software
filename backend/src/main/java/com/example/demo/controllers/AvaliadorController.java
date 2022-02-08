package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;
import com.example.demo.services.AvaliadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliador")
public class AvaliadorController {
    @Autowired
    private AvaliadorService avaliadorService;

    @GetMapping
    public List<Card> getCards() {
        return avaliadorService.getAllCards();
    }
}
