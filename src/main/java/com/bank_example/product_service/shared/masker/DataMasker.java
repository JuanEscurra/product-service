package com.bank_example.product_service.shared.masker;

public interface DataMasker {

    String maskId(String value, int maskLength);
    String maskEmail(String email);
    String maskPhoneNumber(String phoneNumber);
    String maskAccount(String account);
}
