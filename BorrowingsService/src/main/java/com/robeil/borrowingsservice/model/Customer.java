package com.robeil.borrowingsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"contact","address"})
public class Customer {

    private int customerNumber;
    private String customerName;

}
