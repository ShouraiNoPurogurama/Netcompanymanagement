package com.se1863.networkcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contract_And_Option")
@IdClass(ContractAndOptionId.class)
public class ContractAndOption {
    @Id
    @ManyToOne
    @JoinColumn(name = "ContractID", referencedColumnName = "ContractID")
    private Contract contract;

    @Id
    @ManyToOne
    @JoinColumn(name = "OptionID", referencedColumnName = "OptionID")
    private Option option;

    @Column(name = "Status")
    private Integer status;
}
