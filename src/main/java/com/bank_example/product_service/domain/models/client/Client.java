package com.bank_example.product_service.domain.models.client;

import com.bank_example.product_service.domain.models.client.value_objects.ClientType;
import com.bank_example.product_service.shared.models.AuditTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Client {

    private String id;
    private ClientType clientType;
    private Person person;
    private Company company;
    private Boolean active;

    public String getIdentifier() {
        if (person != null) {
            return person.getIdentifier();
        } else if (company != null) {
            return company.getIdentifier();
        }
        return null;
    }

    public String getCategory() {
        if (person != null) {
            return person.getCategory().name();
        } else if (company != null) {
            return company.getCategory().name();
        }
        return null;
    }
}
