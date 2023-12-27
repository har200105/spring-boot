package com.example.transactions.Transactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.example.transactions.Transactions.entities.Transaction;
import com.example.transactions.Transactions.repository.TransactionRepository;
import com.example.transactions.Transactions.service.TransactionService;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public void addTransaction(Transaction transaction) {
        if ("USD".equals(transaction.getIncomingCurrency())) {
            BigDecimal usdAmount = transaction.getAmount();
            BigDecimal inrAmount = usdAmount.multiply(new BigDecimal("83.17"));
    
            Transaction inrTransaction = new Transaction();
            inrTransaction.setDate(transaction.getDate());
            inrTransaction.setDescription(transaction.getDescription());
            inrTransaction.setAmount(inrAmount);
            inrTransaction.setType(transaction.getType());
            inrTransaction.setIncomingCurrency(transaction.getIncomingCurrency()); 
    
            transactionRepository.save(inrTransaction);
        } else {
            transactionRepository.save(transaction);
        }
    }
    
}
