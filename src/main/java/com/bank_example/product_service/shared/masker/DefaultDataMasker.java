package com.bank_example.product_service.shared.masker;

import org.springframework.stereotype.Component;

@Component
public class DefaultDataMasker implements DataMasker {

    @Override
    public String maskId(String value, int maskLength) {
        if (value == null || value.length() <= maskLength) return value;

        StringBuilder maskedValue = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            if (i < maskLength) maskedValue.append("*");
            else maskedValue.append(value.charAt(i));
        }
        return maskedValue.toString();
    }

    @Override
    public String maskEmail(String email) {
        if (email == null || email.isEmpty()) return email;

        String[] parts = email.split("@");
        if (parts.length != 2) return email;

        String localPart = parts[0];
        String domainPart = parts[1];
        if (localPart.length() <= 2) return email;

        String maskedLocalPart = localPart.substring(0, 2) + "*".repeat(localPart.length() - 2);
        return maskedLocalPart + "@" + domainPart;
    }

    @Override
    public String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 4) return phoneNumber;

        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i < phoneNumber.length() - 4) {
                maskedNumber.append("*");
            } else {
                maskedNumber.append(phoneNumber.charAt(i));
            }
        }
        return maskedNumber.toString();
    }

    @Override
    public String maskAccount(String account) {

        return this.maskId(account, 5);
    }
}
