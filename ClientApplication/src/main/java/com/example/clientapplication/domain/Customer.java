package com.example.clientapplication.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    private Integer customerNumber;
    private String customerName;
    private Contact contact;
    private Address address;

}
