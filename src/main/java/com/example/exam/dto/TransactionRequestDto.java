package com.example.exam.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @NotNull(message = "ID счета отправителя обязателен")
    private Integer senderAccountId;

    @NotNull(message = "ID счета получателя обязателен")
    private Integer receiverAccountId;

    @DecimalMin(value = "0.01", message = "Сумма должна быть больше 0")
    private double amount;
}
