package com.example.transactions.Transactions.service;

import java.time.LocalDate;
import java.util.List;

import com.example.transactions.Transactions.entities.Transaction;


public interface TransactionService {
    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByDate(LocalDate date);

    void addTransaction(Transaction transaction);
}
