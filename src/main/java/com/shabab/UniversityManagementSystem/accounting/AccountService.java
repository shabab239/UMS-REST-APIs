package com.shabab.UniversityManagementSystem.accounting;

import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 01/09/2024
 */

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ApiResponse recordTransaction(Long accountId, Double amount, String description) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new NoSuchElementException("Account not found with ID: " + accountId));

            Transaction.TransactionType transactionType;
            if (amount >= 0) {
                transactionType = Transaction.TransactionType.CREDIT;
                account.setBalance(account.getBalance() + amount);
            } else {
                transactionType = Transaction.TransactionType.DEBIT;
                account.setBalance(account.getBalance() + amount);
            }

            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setAmount(amount);
            transaction.setTransactionType(transactionType);
            transaction.setAccount(account);
            transaction.setDescription(description);

            transactionRepository.save(transaction);
            apiResponse.setSuccessful(true);
            return apiResponse;
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            return apiResponse;
        }
    }

}
