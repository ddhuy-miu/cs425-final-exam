package miu.edu.finalexam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Account Number is required!")
    private String accountNumber;

    @Column(nullable = false)
    @NotBlank(message = "Customer Name is required!")
    private String customerName;

    @Column(nullable = false)
    @NotBlank(message = "Account Type is required!")
    private String accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate lastTransactionDate;

    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime lastTransactionTime;
}
