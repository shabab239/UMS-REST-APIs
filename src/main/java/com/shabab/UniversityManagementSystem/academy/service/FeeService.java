package com.shabab.UniversityManagementSystem.academy.service;


import com.shabab.UniversityManagementSystem.academy.model.Fee;
import com.shabab.UniversityManagementSystem.academy.model.FeeImposed;
import com.shabab.UniversityManagementSystem.academy.model.Student;
import com.shabab.UniversityManagementSystem.academy.repository.FeeImposedRepository;
import com.shabab.UniversityManagementSystem.academy.repository.FeeRepository;
import com.shabab.UniversityManagementSystem.academy.repository.SemesterRepository;
import com.shabab.UniversityManagementSystem.academy.repository.StudentRepository;
import com.shabab.UniversityManagementSystem.accounting.Account;
import com.shabab.UniversityManagementSystem.accounting.AccountRepository;
import com.shabab.UniversityManagementSystem.accounting.Transaction;
import com.shabab.UniversityManagementSystem.accounting.TransactionRepository;
import com.shabab.UniversityManagementSystem.security.model.User;
import com.shabab.UniversityManagementSystem.security.repository.UserRepository;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import com.shabab.UniversityManagementSystem.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FeeImposedRepository feeImposedRepository;

    public ApiResponse getAll() {
        ApiResponse response = new ApiResponse();
        try {
            List<Fee> fees = feeRepository.getAll(
                    AuthUtil.getCurrentUniversityId()
            ).orElse(new ArrayList<>());
            if (fees.isEmpty()) {
                return response.returnError("No fees found");
            }
            response.setData("fees", fees);
            response.success("Fees retrieved successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    public ApiResponse getById(Long id) {
        ApiResponse response = new ApiResponse();
        try {
            Fee fee = feeRepository.getById(
                    id, AuthUtil.getCurrentUniversityId()
            ).orElse(new Fee());
            if (fee.getId() == null) {
                return response.returnError("Fee not found");
            }
            response.setData("fee", fee);
            response.success("Successfully retrieved fee");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    public ApiResponse getImposedFees(Long studentId) {
        ApiResponse response = new ApiResponse();
        try {
            List<FeeImposed> fees = feeImposedRepository.findAllByStudentId(
                    studentId
            ).orElse(new ArrayList<>());
            if (fees.isEmpty()) {
                return response.returnError("No fees found");
            }
            response.setData("fees", fees);
            response.success("Fees retrieved successfully");
            return response;
        } catch (Exception e) {
            return response.returnError(e);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse saveFees(List<Fee> fees) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    AuthUtil.getCurrentUserId(), AuthUtil.getCurrentUniversityId()
            ).orElse(null);

            if (user == null) {
                return response.returnError("User not found");
            }

            feeRepository.saveAll(fees);

            //Imposing fees
            Account userAccount = user.getAccount();
            if (userAccount == null) {
                Account account = new Account();
                account.setName(user.getName() + " Cash A/C");
                account.setBalance(0.0);
                account = accountRepository.save(account);

                user.setAccount(account);
                user = userRepository.save(user);
                userAccount = user.getAccount();
            }

            double totalFees = 0.0;

            for (Fee fee : fees) {
                List<Student> students = studentRepository.findAllBySemester(
                        fee.getSemester().getId(), AuthUtil.getCurrentUniversityId()
                ).orElse(null);

                if (students != null) {
                    for (Student student : students) {
                        FeeImposed feeImposed = new FeeImposed();
                        feeImposed.setStudent(student);
                        feeImposed.setFee(fee);
                        feeImposed.setAmount(fee.getAmount());
                        feeImposedRepository.save(feeImposed);

                        Account studentAccount = student.getAccount();
                        if (studentAccount == null) {
                            Account account = new Account();
                            account.setName(student.getName() + " Cash A/C");
                            account.setBalance(0.0);
                            account = accountRepository.save(account);

                            student.setAccount(account);
                            student = studentRepository.save(student);
                            studentAccount = student.getAccount();
                        }

                        Transaction transaction = new Transaction();
                        transaction.setAccount(studentAccount);
                        transaction.setAmount(feeImposed.getAmount());
                        transaction.setTransactionType(Transaction.TransactionType.DEBIT);
                        transaction.setTimestamp(LocalDateTime.now());
                        transaction.setDescription("Fee imposed for " + fee.getType());
                        transaction.setUniversity(AuthUtil.getCurrentUniversity());
                        transactionRepository.save(transaction);

                        studentAccount.setBalance(studentAccount.getBalance() - feeImposed.getAmount());
                        accountRepository.save(studentAccount);

                        Transaction userTransaction = new Transaction();
                        userTransaction.setAccount(userAccount);
                        userTransaction.setAmount(feeImposed.getAmount());
                        userTransaction.setTransactionType(Transaction.TransactionType.CREDIT);
                        userTransaction.setTimestamp(LocalDateTime.now());
                        userTransaction.setDescription("Fee imposed for " + fee.getType() + " for " + student.getName());
                        userTransaction.setUniversity(AuthUtil.getCurrentUniversity());
                        transactionRepository.save(userTransaction);

                        totalFees += feeImposed.getAmount();
                    }
                }
            }

            userAccount.setBalance(userAccount.getBalance() + totalFees);
            accountRepository.save(userAccount);

            response.success("Fees saved and imposed successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;
    }

    @Transactional(rollbackOn = Exception.class)
    public ApiResponse collectFees(List<Fee> fees) {
        ApiResponse response = new ApiResponse();
        try {
            User user = userRepository.findById(
                    AuthUtil.getCurrentUserId(), AuthUtil.getCurrentUniversityId()
            ).orElse(null);

            if (user == null) {
                return response.returnError("User not found");
            }

            Account userAccount = user.getAccount();
            if (userAccount == null) {
                return response.returnError("User account not found");
            }

            double totalFees = 0.0;

            for (Fee fee : fees) {
                List<Student> students = studentRepository.findAllBySemester(
                        fee.getSemester().getId(), AuthUtil.getCurrentUniversityId()
                ).orElse(null);

                if (students != null) {
                    for (Student student : students) {
                        FeeImposed feeImposed = feeImposedRepository.findByStudentAndFee(
                                student.getId(), fee.getId()
                        ).orElse(null);

                        if (feeImposed == null) {
                            return response.returnError("Fee not imposed for " + student.getName());
                        }

                        Account studentAccount = student.getAccount();
                        if (studentAccount == null) {
                            return response.returnError("Student account not found");
                        }

                        Transaction transaction = new Transaction();
                        transaction.setAccount(studentAccount);
                        transaction.setAmount(feeImposed.getAmount());
                        transaction.setTransactionType(Transaction.TransactionType.CREDIT);
                        transaction.setTimestamp(LocalDateTime.now());
                        transaction.setDescription("Fee collected for " + fee.getType());
                        transaction.setUniversity(AuthUtil.getCurrentUniversity());
                        transactionRepository.save(transaction);

                        studentAccount.setBalance(studentAccount.getBalance() + feeImposed.getAmount());
                        accountRepository.save(studentAccount);

                        Transaction userTransaction = new Transaction();
                        userTransaction.setAccount(userAccount);
                        userTransaction.setAmount(feeImposed.getAmount());
                        userTransaction.setTransactionType(Transaction.TransactionType.DEBIT);
                        userTransaction.setTimestamp(LocalDateTime.now());
                        userTransaction.setDescription("Fee collected for " + fee.getType() + " for " + student.getName());
                        userTransaction.setUniversity(AuthUtil.getCurrentUniversity());
                        transactionRepository.save(userTransaction);

                        totalFees += feeImposed.getAmount();
                    }
                }
            }

            userAccount.setBalance(userAccount.getBalance() - totalFees);
            accountRepository.save(userAccount);

            response.success("Fees collected successfully");
        } catch (Exception e) {
            return response.returnError(e);
        }
        return response;

    }

}

