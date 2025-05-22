package com.bank_example.product_service.application.ports.out.http.request;

import lombok.Data;

@Data
public class Client {

    private String id;
    private ClientType clientType;
    private ClientCategory userCategory;
    private Boolean active;
}
