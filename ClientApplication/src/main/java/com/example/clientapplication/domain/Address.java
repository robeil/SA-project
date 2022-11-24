package com.example.clientapplication.domain;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Address {

    private String street;
    private String city;
    private String zip;

}
