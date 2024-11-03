package com.shabab.UniversityManagementSystem.accounting.service;

import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.accounting.model.Account;
import com.shabab.UniversityManagementSystem.accounting.model.Transaction;
import com.shabab.UniversityManagementSystem.accounting.repository.AccountRepository;
import com.shabab.UniversityManagementSystem.accounting.repository.TransactionRepository;
import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    /*public ApiResponse recordTransaction(Long accountId, Double amount, String description) {
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
    }*/

    @Transactional(readOnly = true)
    public ApiResponse getJournal() {
        ApiResponse apiResponse = new ApiResponse(true);
        try {
            List<Transaction> transactions = transactionRepository.findByUniversityId(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Map<String, Object>> journalEntries = transactions.stream().map(transaction -> {
                Map<String, Object> entry = new HashMap<>();
                entry.put("date", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(transaction.getTimestamp()));
                entry.put("accountName", transaction.getAccount().getTitle());
                entry.put("description", transaction.getDescription());
                if (transaction.getTransactionType() == Transaction.TransactionType.DEBIT) {
                    entry.put("debit", transaction.getAmount());
                    entry.put("credit", 0.0);
                } else {
                    entry.put("debit", 0.0);
                    entry.put("credit", transaction.getAmount());
                }
                return entry;
            }).collect(Collectors.toList());

            Map<String, Object> data = new HashMap<>();
            data.put("journalEntries", journalEntries);

            apiResponse.setData(data);
            apiResponse.setMessage("Journal entries generated successfully");
        } catch (Exception e) {
            apiResponse.setSuccessful(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @Transactional(readOnly = true)
    public ApiResponse getBalanceSheet() {
        ApiResponse response = new ApiResponse(true);
        try {
            List<User> allUsers = userRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Student> allStudents = studentRepository.findAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());

            List<Account> allAccounts = new ArrayList<>();
            allUsers.forEach(user -> {
                if (user.getAccount() != null) {
                    allAccounts.add(user.getAccount());
                }
            });
            allStudents.forEach(student -> {
                if (student.getAccount() != null) {
                    allAccounts.add(student.getAccount());
                }
            });

            double totalCashAssets = allAccounts.stream()
                    .mapToDouble(Account::getBalance)
                    .sum();

            List<Account> leftSideAccounts = allAccounts.stream()
                    .filter(account -> account.getBalance() >= 0)
                    .collect(Collectors.toList());

            List<Account> rightSideAccounts = allAccounts.stream()
                    .filter(account -> account.getBalance() < 0)
                    .collect(Collectors.toList());

            Map<String, Object> balanceSheetData = new HashMap<>();
            balanceSheetData.put("totalCashAssets", totalCashAssets);
            balanceSheetData.put("leftSide", leftSideAccounts);
            balanceSheetData.put("rightSide", rightSideAccounts);

            response.setData(balanceSheetData);
            response.setMessage("Balance sheet generated successfully");
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
