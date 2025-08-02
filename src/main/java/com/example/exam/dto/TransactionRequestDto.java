package com.example.exam.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @NotNull(message = "ID счета отправителя обязателен")
    private Integer senderAccountId;

    @NotNull(message = "ID счета получателя обязателен")
    private Integer receiverAccountId;

    @NotBlank(message = "Номер счета отправителя обязателен")
    private String senderAccount;

    @NotBlank(message = "Номер счета получателя обязателен")
    private String receiverAccount;

    @DecimalMin(value = "0.01", message = "Сумма должна быть больше 0")
    private double amount;
}
