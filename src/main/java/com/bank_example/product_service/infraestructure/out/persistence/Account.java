package com.bank_example.product_service.infraestructure.out.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "accounts")
@Data
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String clientId;
    private BigDecimal balance;
    private LocalDate openedDate;
    private Boolean active;
    private AccountType accountType;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal maintenceFee;


    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal interestRate;
    private LocalDate maturityDate;


    private Integer currentWithdrawalCount;

}
