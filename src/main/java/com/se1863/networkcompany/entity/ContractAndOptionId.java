package com.se1863.networkcompany.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractAndOptionId implements Serializable {
    private String contract;
    private String option;
}
