package miu.edu.finalexam.repositories;

import miu.edu.finalexam.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(String accountNumber);

    List<Account> findAccountsByLastTransactionDateBefore(LocalDate date);

    List<Account> findAccountsByLastTransactionDateIsAfter(LocalDate date);

    void deleteAccountByAccountNumber(String accountNumber);
}
