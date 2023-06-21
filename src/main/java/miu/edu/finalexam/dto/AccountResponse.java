package miu.edu.finalexam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AccountResponse(
        Long accountId,
        String accountNumber,
        String customerName,
        String accountType,
        Double balance,
        LocalDate lastTransactionDate,
        LocalTime lastTransactionTime
) {
}
