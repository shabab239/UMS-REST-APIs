package com.shabab.UniversityManagementSystem.accounting.restcontroller;

import com.shabab.UniversityManagementSystem.accounting.service.AccountService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 02/09/2024
 */

@CrossOrigin
@RestController
@RequestMapping("/api/accounting")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getJournal")
    public ApiResponse getJournal() {
        return accountService.getJournal();
    }

    @GetMapping("/getBalanceSheet")
    public ApiResponse getBalanceSheet() {
        return accountService.getBalanceSheet();
    }

}
