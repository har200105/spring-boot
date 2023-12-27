package com.example.transactions.Transactions.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactions.Transactions.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDate(LocalDate date);
}
