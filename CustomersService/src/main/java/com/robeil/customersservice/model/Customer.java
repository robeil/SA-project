package com.robeil.customersservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Customer {

    @Id
    private int customerNumber;
    private String customerName;
    private Contact contact;
    private Address address;


}
