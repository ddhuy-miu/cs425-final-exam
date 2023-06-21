package miu.edu.finalexam.services;

import miu.edu.finalexam.models.Account;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    List<Account> getDormantAccounts();

    List<Account> getActiveAccounts();

    Account getAccountById(Long accountId);

    Account getAccountByNumber(String accountNumber);

    Account createAccount(String accountNumber, String customerName, String accountType,
                          Double balance, LocalDate transactionDate, LocalTime transactionTime);

    Account updateAccount(Long accountId, String accountNumber, String customerName, String accountType,
                          Double balance, LocalDate transactionDate, LocalTime transactionTime);

    void deleteAccountById(Long accountId);

    void deleteAccountByNumber(String accountNumber);

    void deleteAccount(Account account);
}
