package com.robeil.borrowingsservice.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeEvent<T> {

    private String operation;
    private T description;

}
