package com.robeil.customersservice.service.dto;

import com.robeil.customersservice.model.Address;
import com.robeil.customersservice.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CustomerDTO {

    private int customerNumber;
    private String customerName;
    private Contact contact;
    private Address address;

}
