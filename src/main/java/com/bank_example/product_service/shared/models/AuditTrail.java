package com.bank_example.product_service.shared.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuditTrail {

    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;

}
