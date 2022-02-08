package com.example.demo.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.repositories.UserAccountsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @Default
    private String hash = UUID.randomUUID().toString();

    @Default
    private Date startDate = new Date();

    private Date dueDate;

    @Default
    private Date createdAt = new Date();
    private Date updatedAt;

    @Default
    private boolean approved = false;

    private int priority;

    @ManyToOne
    @JsonIgnore
    private UserAccount owner;

    @ManyToOne
    @JsonIgnore
    private UserAccount developer;

}