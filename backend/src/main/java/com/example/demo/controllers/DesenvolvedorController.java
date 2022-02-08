package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Card;
import com.example.demo.services.AvaliadorService;
import com.example.demo.services.DesenvolvedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desenvolvedor")
public class DesenvolvedorController {

    @Autowired
    private DesenvolvedorService desenvolvedorService;

    @GetMapping
    public List<Card> getCards() {
        return desenvolvedorService.getAllCardByDeveloper();
    }
}
