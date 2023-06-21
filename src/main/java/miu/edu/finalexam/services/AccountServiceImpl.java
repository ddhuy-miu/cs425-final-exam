package miu.edu.finalexam.services;

import miu.edu.finalexam.models.Account;
import miu.edu.finalexam.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository repository;

    @Override
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    @Override
    public List<Account> getDormantAccounts() {
        LocalDate today = LocalDate.now();
        LocalDate oneMonthBefore = today.minusMonths(1);
        return repository.findAccountsByLastTransactionDateBefore(oneMonthBefore);
    }

    @Override
    public List<Account> getActiveAccounts() {
        LocalDate today = LocalDate.now();
        LocalDate inOneMonth = today.minusMonths(1);
        inOneMonth = inOneMonth.minusDays(1);
        return repository.findAccountsByLastTransactionDateIsAfter(inOneMonth);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return repository.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return repository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account createAccount(String accountNumber, String customerName, String accountType,
                                 Double balance, LocalDate transactionDate,
                                 LocalTime transactionTime) {
        Account account = new Account(null, accountNumber, customerName, accountType,
                balance, transactionDate, transactionTime);
        repository.save(account);
        return account;
    }

    @Override
    public Account updateAccount(Long accountId, String accountNumber, String customerName,
                                 String accountType, Double balance, LocalDate transactionDate,
                                 LocalTime transactionTime) {
        Account account = repository.findById(accountId).orElse(null);
        if (account != null) {
            account.setAccountNumber(accountNumber);
            account.setCustomerName(customerName);
            account.setAccountType(accountType);
            account.setBalance(balance);
            account.setLastTransactionDate(transactionDate);
            account.setLastTransactionTime(transactionTime);
            repository.save(account);
        }
        return account;
    }

    @Override
    public void deleteAccountById(Long accountId) {
        repository.deleteById(accountId);
    }

    @Override
    public void deleteAccountByNumber(String accountNumber) {
        repository.deleteAccountByAccountNumber(accountNumber);
    }

    @Override
    public void deleteAccount(Account account) {
        repository.delete(account);
    }
}
