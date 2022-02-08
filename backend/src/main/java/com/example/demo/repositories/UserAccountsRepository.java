package com.example.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

import com.example.demo.models.UserAccount;

public interface UserAccountsRepository extends PagingAndSortingRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
