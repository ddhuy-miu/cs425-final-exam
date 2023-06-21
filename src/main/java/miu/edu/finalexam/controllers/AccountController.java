package miu.edu.finalexam.controllers;

import miu.edu.finalexam.dto.AccountRequest;
import miu.edu.finalexam.dto.AccountResponse;
import miu.edu.finalexam.models.Account;
import miu.edu.finalexam.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ezbank/api/account")
public class AccountController {
    @Autowired
    AccountService service;

    @GetMapping(value = {"/dormant/list"})
    public ResponseEntity<List<AccountResponse>> getDormantAccounts() {
        List<Account> dormantAccounts = service.getDormantAccounts();
        List<AccountResponse> responses = dormantAccounts.stream()
                .sorted(Comparator.comparing(Account::getCustomerName))
                .map(a -> new AccountResponse(
                        a.getAccountId(),
                        a.getAccountNumber(),
                        a.getCustomerName(),
                        a.getAccountType(),
                        a.getBalance(),
                        a.getLastTransactionDate(),
                        a.getLastTransactionTime()
                )).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = {"/active/list"})
    public ResponseEntity<List<AccountResponse>> getActiveAccounts() {
        List<Account> activeAccounts = service.getActiveAccounts();
        List<AccountResponse> responses = activeAccounts.stream()
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .map(a -> new AccountResponse(
                        a.getAccountId(),
                        a.getAccountNumber(),
                        a.getCustomerName(),
                        a.getAccountType(),
                        a.getBalance(),
                        a.getLastTransactionDate(),
                        a.getLastTransactionTime()
                )).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = {"totalBalance"})
    public ResponseEntity<Object> getTotalAmounts() {
        Double totalAmounts = 0.0;
        List<Account> accounts = service.getAccounts();
        for (Account a: accounts)
            totalAmounts += a.getBalance();
        Map<String, Object> response = new HashMap<>();
        response.put("totalBankBalance", totalAmounts);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "new")
    public ResponseEntity<AccountResponse> createNewAccount(@RequestBody AccountRequest request) {
        Account account = service.createAccount(
                request.accountNumber(),
                request.customerName(),
                request.accountType(),
                request.balance(),
                request.lastTransactionDate(),
                request.lastTransactionTime()
        );
        AccountResponse response = new AccountResponse(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getCustomerName(),
                account.getAccountType(),
                account.getBalance(),
                account.getLastTransactionDate(),
                account.getLastTransactionTime()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
