package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Card;
import com.example.demo.models.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByOwner(UserAccount owner);

    Optional<Card> findByIdAndOwner(Long id, UserAccount owner);

    Optional<Card> findByHashAndOwner(String hash, UserAccount owner);

    List<Card> findByDeveloper(UserAccount developer);
}
